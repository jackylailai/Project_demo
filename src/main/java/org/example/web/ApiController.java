package org.example.web;

import org.example.entity.Tip;
import org.example.entity.User;
import org.example.model.LoginRequest;
import org.example.model.UserDataResponse;
import org.example.service.sql.AttendanceService;
import org.example.service.sql.TipService;
import org.example.service.sql.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

import static org.example.service.SHAService.getSHA256StrJava;

@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
public class ApiController {


    @Autowired
    private UserService userService;
    private AttendanceService attendanceService;
    @Autowired
    public ApiController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }
    @GetMapping("/init")
    public String init(){
        User user = null;//為了確保每個迴圈迭代都使用獨立的 User 物件，以便在每次循環中填充不同的數據。
        for(int i=0;i<10;i++){
            user = new User();
            user.setName("test"+i);
            user.setUsername("username"+i);
            String password = ("password"+i);
            String sha256HashPassword = getSHA256StrJava(password);
            System.out.println("SHA-256 雜湊值(存入資料庫密碼欄位): " + sha256HashPassword);
            user.setPassword(sha256HashPassword);
            user.setGrade((long) (i*10));
            userService.save(user);
        }
        return "初始化完成。";
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        User user = userService.findByUsername(loginRequest.getUsername());
        //我只要抓到這個entity我就可以對其資料庫的內容作核對，後方的.getPassword
        //接著我需要將取得的password做hash加密確認是否一樣
        String sha256HashConfirm = getSHA256StrJava(loginRequest.getPassword());
        System.out.println("SHA-256 雜湊值(user輸入的password): " + sha256HashConfirm);
        System.out.println("SHA-256 雜湊值(資料庫實際此user的密碼欄位): " + user.getPassword());
        if (user != null && user.getPassword().equals(sha256HashConfirm)) {
            int level = user.getLevel(); //拿level
            String name = user.getName();

            // 生成 Session ID
            String sessionId = session.getId();
            session.setAttribute("sessionId", sessionId);
            session.setAttribute("username", loginRequest.getUsername());
            Date creationTime = new Date();
            session.setAttribute("creationTime",creationTime);
            Date expirationTime = new Date(creationTime.getTime() + session.getMaxInactiveInterval() * 1000L);
            session.setAttribute("expirationTime", expirationTime);
            System.out.println(creationTime);
            System.out.println(expirationTime);
            System.out.println("登入成功你的sessionId:" + sessionId);
//            Enumeration<String> attributeNames = session.getAttributeNames();
//            while (((Enumeration<?>) attributeNames).hasMoreElements()) {
//                String attributeName = attributeNames.nextElement();
//                String attributeValue = (String) session.getAttribute(attributeName);
//                System.out.println("session內容物名稱:" + attributeName);
//                System.out.println("session內容物:" + attributeValue);
//            }
            UserDataResponse userDataResponse = new UserDataResponse();
            userDataResponse.setLevel(level); // 設置 level
            userDataResponse.setName(name);
            return ResponseEntity.ok(userDataResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("登入失敗，帳號或密碼錯誤");
        }
    }
    @GetMapping("/userData")
    public ResponseEntity<?> getUserData(HttpSession session) {
        // 從 Session 中獲取用戶的 Session ID
        String sessionId = session.getId();
        String username = (String) session.getAttribute("username");
        Date creationTime = (Date) session.getAttribute("creationTime"); // 取得創建時間
        // 計算到期時間
        Date expirationTime = (Date) session.getAttribute("expirationTime");

        System.out.println("網頁抓到你的sessionid:"+ sessionId);
        System.out.println("sessionid creationTime:"+ creationTime);
        System.out.println("sessionid expirationTime"+ expirationTime);
        System.out.println("username:"+ username);
        System.out.println("session物件類型和記憶體位址:" +session.getAttributeNames());
        // 根據 Session ID 查找用戶資料
        User user = userService.findByUsername(username);

        boolean isNewSession = session.isNew();
        if (!isNewSession && sessionId != null && username != null) {
            UserDataResponse userDataResponse = new UserDataResponse();
            userDataResponse.setName(user.getName());
            userDataResponse.setGrade(user.getGrade());
            System.out.println("userDataResponse:"+userDataResponse);
            return ResponseEntity.ok(userDataResponse); // 回傳用戶名字和成績資料
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session ID 無效 or 尚未登入"); // 用戶未登入或 Session ID 無效
        }
    }
    @GetMapping("/api/score")
    public ResponseEntity<List<Object[]>> getScore(HttpSession session) {
        String sessionId = session.getId();
        String username = (String) session.getAttribute("username");
        System.out.println("sessionId:"+sessionId);
        User user = userService.findByUsername(username);
        List<Object[]> scoreData = attendanceService.getScoreDetailsByUsername(username);
        return ResponseEntity.ok(scoreData);
    }
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("登出成功");
    }
    @GetMapping("/student-list")
    public List<?> getStudentList(@RequestParam(value="student-username",required = false) String username) {
        if (username != null && !username.isEmpty()) {
            //提供關鍵字搜尋人的名字
            List<User> studentList = userService.searchStudentsByKeyword(username);
            return studentList;
        } else {
            List<String> allStudents = userService.getAllStudentNames();
            return allStudents;
        }
    }
    @GetMapping("/userByName/{username}")
    public User getUserByName(@PathVariable("username") String username){

        return userService.getByName(username);
    }

    @GetMapping("/userById/{userid}")
    public User getUserById(@PathVariable("userid") Long userid){

        return userService.getUserByID(userid);
    }
    @GetMapping("/apitest")
    public User gettest(){
        System.out.println("userservice"+userService);
        User user = userService.findByUsername("username3");
        System.out.println("user"+user);
        return user;
    }
    @GetMapping("/page")
    public Page<User> getPage(){

        return userService.findPage();
    }
    @PostMapping("/insert")
    public User insertUser(@RequestBody User user) {

        return userService.saveUser(user);
    }
}

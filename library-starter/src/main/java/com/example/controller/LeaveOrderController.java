package com.example.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.constant.ActivitiDefinitionKeysConstant;
import com.example.request.LeaveInfoRequest;
import com.example.response.LeaveOrderActivitiResponse;
import com.example.result.Results;
import com.example.service.LeaveService;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/5/19 21:24
 */
@RestController
@RequestMapping("/leave")
public class LeaveOrderController {
    private Logger logger = LoggerFactory.getLogger(LeaveOrderController.class);

    @Autowired
    private LeaveService leaveService;

    @RequestMapping(value = "/startProcess", method = RequestMethod.POST)
    public Results<LeaveOrderActivitiResponse> startRequestLeave(@RequestBody LeaveInfoRequest leaveInfoRequest) {
        try {
            logger.info("request coming: "+leaveInfoRequest);
            Map<String, Object> variable = new HashMap<>();
            variable.put("info", leaveInfoRequest);
            variable.put("creator", "陈磊");
            variable.put("hr", "汪涵");
            variable.put("pm", "李虎");
            variable.put("spm", "杨帆");
            variable.put("extra", "请假流程");
            ProcessInstance processInstance = leaveService.startProcess(ActivitiDefinitionKeysConstant.LEAVE_ACTIVITI, variable);
            LeaveOrderActivitiResponse response = new LeaveOrderActivitiResponse();
            response.setInstanceId(processInstance.getId());
            response.setLeaveReason(leaveInfoRequest.getLeaveReason());
            response.setLeaveType(leaveInfoRequest.getLeaveType());
            logger.info("response show here: "+response);
            logger.info("request processed completely");
            return Results.success(response);
        } catch (Exception e) {
            return Results.fail(null);
        }
    }

    @RequestMapping(value = "/testCapture", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ok')")
    public Results<LeaveOrderActivitiResponse> testCapture(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        System.out.println(authorities);
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("name: "+name);

        return Results.success("测试抓包");
    }

    private String createJwt(String username) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 5);

        String sign = JWT.create().withSubject("web json token")
                .withClaim("username", username)
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256("chengfen"));

        System.out.println("jwt will expire at "+format.format(calendar.getTime()));
        return sign;
    }

    private boolean verify(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("chengfen")).build();
        DecodedJWT jwt = verifier.verify(token);
        if (jwt == null) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new String(Base64.getDecoder().decode("eyJhdXRocyI6eyJsb2NhbGhvc3Q6NTAwMCI6eyJ1c2VybmFtZSI6ImNoZW5sZWkiLCJwYXNzd29yZCI6IjEyMzQ1NiIsImF1dGgiOiJZMmhsYm14bGFUb3hNak0wTlRZPSJ9fX0=")));
    }

}

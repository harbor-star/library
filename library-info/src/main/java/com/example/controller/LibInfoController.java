package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dto.MusicPlayerDto;
import com.example.entity.LibInfoEntity;
import com.example.mapper.LibInfoMapper;
import com.example.request.InformationRequest;
import com.example.request.QueryConditionRequest;
import com.example.service.LibInfoService;
import com.example.service.MusicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/4/2 14:24
 */
@RestController
@CrossOrigin
public class LibInfoController {

    @Autowired
    private LibInfoMapper libInfoMapper;

    @Autowired
    private LibInfoService libInfoService;

    @Autowired
    private MusicInfoService musicInfoService;

    @PostMapping("/getLibInfo")
    public List<LibInfoEntity> getLibInfo(HttpServletResponse response, @RequestBody InformationRequest informationRequest) {
        List<LibInfoEntity> libInformations = libInfoMapper.getLibInformations(informationRequest.getInfoCollection());
        if (!CollectionUtils.isEmpty(libInformations)) {
            return libInformations;
        } else {
            return null;
        }
    }

    @PostMapping("/search")
    public Page<LibInfoEntity> search(@RequestBody QueryConditionRequest request) {
        Page<LibInfoEntity> page = new Page<>(request.getPageNum(), request.getPageSize());
        return libInfoMapper.getLibInfoByCondition(page, request);
    }

    @PostMapping("/pageContentByCondition")
    public IPage<LibInfoEntity> pageContentByCondition(@RequestBody QueryConditionRequest request) {
        Page<LibInfoEntity> page = new Page<>(request.getPageNum(), request.getPageSize());
        return libInfoMapper.getLibInfoByCondition(page, request);
    }

    @GetMapping("/getMusicInfo")
    public MusicPlayerDto getMusicInfo() {
        return musicInfoService.getMusicInfo();
    }
}

package com.gyub.accountbook.web.sharing.controller;

import com.gyub.accountbook.global.dto.ResultListResponse;
import com.gyub.accountbook.global.dto.sharing.SharingDto;
import com.gyub.accountbook.global.dto.sharing.SharingRequestDto;
import com.gyub.accountbook.web.sharing.domain.Sharing;
import com.gyub.accountbook.web.sharing.service.SharingService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SharingController {
    private final SharingService sharingService;


    //공유조회
    @GetMapping("/sharing/{sharingId}")
    public ResponseEntity<SharingDto> findSharingById() {
        return ResponseEntity.ok()
                .body(null);
    }

    @GetMapping("/sharing")
    public ResponseEntity<ResultListResponse> getAllSahring() {
        List<SharingDto> sharings = sharingService.findByAll();
        return ResponseEntity.ok()
                .body(new ResultListResponse(sharings, sharings.size()));
    }

    //공유신청
    @PostMapping("/sharing")
    public ResponseEntity<SharingDto> saveSharing(
            @RequestBody SharingRequestDto sharingRequestDto
    ) {
        return ResponseEntity.ok()
                .body(sharingService.save(
                        sharingRequestDto.getFromMemberId()
                        , sharingRequestDto.getToMemberId()
                        , sharingRequestDto.getAccountId()
                ));
    }

    //공유응답
    @PutMapping("/sharing")
    public ResponseEntity<SharingDto> reply(
            @RequestBody SharingRequestDto sharingRequestDto
    ) {
        return ResponseEntity.ok()
                .body(sharingService.replyInvite(sharingRequestDto.getId()
                        , sharingRequestDto.getSharingState()));
    }
    //공유삭제
    @DeleteMapping("/sharing")
    public ResponseEntity<SharingResponseDto> deleteSharing(
            @RequestBody SharingRequestDto sharingRequestDto
    ) {
        sharingService.cancelInvite(sharingRequestDto.getId());
        return ResponseEntity.ok()
                .body(new SharingResponseDto("정삭적으로 삭제 되었습니다."));
    }


    @Data
    @AllArgsConstructor
    static class SharingResponseDto {
        private String message;
    }
}

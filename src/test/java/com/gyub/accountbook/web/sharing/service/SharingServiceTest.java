package com.gyub.accountbook.web.sharing.service;

import com.gyub.accountbook.web.sharing.domain.Sharing;
import com.gyub.accountbook.web.sharing.domain.SharingState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
public class SharingServiceTest {

    @Autowired
    SharingService sharingService;

    @Test
    @Rollback(value = false)
    public void 공유_신청(){
        //Given
        Long toMemberId = 1L;
        Long fromMemberId = 2L;
        Long accountId = 3L;

        //When
        Long sharingId = sharingService.invite(toMemberId, fromMemberId, accountId);


        //Then
        assertEquals(SharingState.INVITE, sharingService.findOne(sharingId).getSharingState());
    }

    @Test
    @Rollback(value = false)
    public void 공유_신청_취소(){
        //Given
        Long sharingId = 6L;

        //When
        sharingService.cancelInvite(sharingId);

        Sharing findSharing = sharingService.findOne(sharingId);

        //Then
        assertNull(findSharing.getId());
    }

    @Test
    @Rollback(value = false)
    public void 공유_응답_승락(){
        //Given
        Long sharingId = 7L;
        SharingState state = SharingState.ACCEPT;

        //When
        sharingService.replyInvite(sharingId, state);

        //Then
        assertEquals(SharingState.ACCEPT, sharingService.findOne(sharingId).getSharingState());

    }
    @Test
    @Rollback(value = false)
    public void 공유_응답_거절(){
        //Given
        Long sharingId = 7L;
        SharingState state = SharingState.ACCEPT;

        //When
        sharingService.replyInvite(sharingId, state);

        //Then
        assertEquals(SharingState.ACCEPT, sharingService.findOne(sharingId).getSharingState());

    }

}
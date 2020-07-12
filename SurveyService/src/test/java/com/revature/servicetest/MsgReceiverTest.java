package com.revature.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.revature.mqlistener.MsgReceiver;

@ExtendWith(SpringExtension.class)

class MsgReceiverTest {

	@Test
	void testConvertStringToTimestamp() {
		Timestamp ts = MsgReceiver.convertStringToTimestamp("07/06/2020 18:15:45");
		assertEquals(ts, MsgReceiver.convertStringToTimestamp("07/06/2020 18:15:45"));
	}

}

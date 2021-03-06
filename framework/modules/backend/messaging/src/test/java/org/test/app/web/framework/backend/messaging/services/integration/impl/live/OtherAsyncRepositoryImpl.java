/**
 *  Copyright (c) 2012 GFT Appverse, S.L., Sociedad Unipersonal.
 *
 *  This Source Code Form is subject to the terms of the Appverse Public License
 *  Version 2.0 (“APL v2.0”). If a copy of the APL was not distributed with this
 *  file, You can obtain one at http://www.appverse.mobi/licenses/apl_v2.0.pdf. [^]
 *
 *  Redistribution and use in source and binary forms, with or without modification,
 *  are permitted provided that the conditions of the AppVerse Public License v2.0
 *  are met.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. EXCEPT IN CASE OF WILLFUL MISCONDUCT OR GROSS NEGLIGENCE, IN NO EVENT
 *  SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 *  LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 *  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT(INCLUDING NEGLIGENCE OR OTHERWISE)
 *  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *  POSSIBILITY OF SUCH DAMAGE.
 */
package org.test.app.web.framework.backend.messaging.services.integration.impl.live;

import javax.jms.Message;

import org.appverse.web.framework.backend.core.enterprise.log.AutowiredLogger;
import org.appverse.web.framework.backend.messaging.services.integration.impl.live.JMSAsyncService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Repository;
import org.test.app.web.framework.backend.messaging.model.integration.SampleDTO;
import org.test.app.web.framework.backend.messaging.services.integration.MyAsyncRepository;

/**
 * OtherAsyncRepository implementation 
 * Purpose of this repository is testing message asynchronous consuming.
 *
 */
@Repository
public class OtherAsyncRepositoryImpl extends JMSAsyncService<SampleDTO> implements
		MyAsyncRepository {

	@AutowiredLogger
	private static Logger logger;

	@Autowired
	private MessageConverter messageConverter;

	/* 
	 * Overwrite this method to process message consumed as JMS Message 
	 * It allows to process JMS Properties.
	 * If you overwrite this method, it isn't mandatory to overwrite processMessage (DTO)
	 */
	@Override
	public void processMessage(final Message message) throws Exception {
		SampleDTO dto = (SampleDTO) getMessageConverter().fromMessage(message);

		String messageId = message.getJMSMessageID();
		dto.setMessageId(messageId);
		this.processMessage(dto);
	}

	/* 
	 * Overwrite this method to process message consumed. 
	 * JMSMessage has already been converted to DTO.
	 */
	@Override
	public void processMessage(final SampleDTO sample) throws Exception {
		logger.info(sample.toString());
		//Convert DTO to Business model
		//Use Injected Business Service
		//Call business Service with Business Model
	}

	/* 
	 * Overwrite this method to provide MessageConverter implementation 
	 */
	@Override
	public MessageConverter getMessageConverter() {
		return messageConverter;
	}

}

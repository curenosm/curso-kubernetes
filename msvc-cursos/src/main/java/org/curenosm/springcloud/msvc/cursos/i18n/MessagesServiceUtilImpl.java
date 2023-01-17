package org.curenosm.springcloud.msvc.cursos.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Implementation of the messages service interface to interact with i18n messages
 *
 * @author Misael Cureño
 * @version 1.0.0
 */
@Component
public class MessagesServiceUtilImpl implements MessagesServiceUtil {

    private final MessageSource messages;

    @Autowired
    public MessagesServiceUtilImpl(MessageSource messages) {
        this.messages = messages;
    }

    public String getGreeting(String name) {
        return messages.getMessage("user.greeting", new String[]{name}, LocaleContextHolder.getLocale());
    }

}

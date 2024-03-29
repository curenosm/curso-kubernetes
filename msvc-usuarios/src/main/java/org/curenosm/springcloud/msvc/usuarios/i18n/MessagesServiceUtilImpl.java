package org.curenosm.springcloud.msvc.usuarios.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessagesServiceUtilImpl implements MessageServiceUtil {

    private final MessageSource messages;

    @Autowired
    public MessagesServiceUtilImpl(MessageSource messages) {
        this.messages = messages;
    }

    public String getGreeting(String name) {
        return messages.getMessage("user.greeting", new String[]{name}, LocaleContextHolder.getLocale());
    }

}

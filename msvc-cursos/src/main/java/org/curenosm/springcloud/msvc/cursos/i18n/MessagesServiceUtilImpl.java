package org.curenosm.springcloud.msvc.cursos.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessagesServiceUtilImpl implements MessagesServiceUtil {

    @Autowired
    private MessageSource messages;

    public String getGreeting(String name) {
        return messages.getMessage("user.greeting", new String[]{name}, LocaleContextHolder.getLocale());
    }

}
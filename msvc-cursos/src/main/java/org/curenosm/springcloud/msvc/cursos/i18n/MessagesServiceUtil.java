package org.curenosm.springcloud.msvc.cursos.i18n;


/**
 * Interface which represents the service to access internationalized messages through the whole application
 *
 * @author Misael Cureño
 * @version 1.0.0
 */
public interface MessagesServiceUtil {
    String getGreeting(String name);
}

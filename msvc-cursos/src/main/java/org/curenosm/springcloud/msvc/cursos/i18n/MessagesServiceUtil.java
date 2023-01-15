package org.curenosm.springcloud.msvc.cursos.i18n;


/**
 * Interface which represents the service to access internationalized messages through the whole application
 *
 * @version 1.0.0
 * @author Misael Cureño
 */
public interface MessagesServiceUtil {
    String getGreeting(String name);
}

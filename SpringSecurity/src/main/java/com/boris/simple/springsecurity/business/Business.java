package com.boris.simple.springsecurity.business;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Business {

    @Secured({"ROLE_BIG_BOSS", "ROLE_COMMON"})
    public void securedLogic() {
        System.out.println("Secured logic done successfully for " +
                SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Secured("ROLE_BIG_BOSS")
    public void verySecuredLogic() {
        System.out.println("Very secured logic done successfully for " +
                SecurityContextHolder.getContext().getAuthentication().getName() +
                " =)");
    }

    /*
        This kind of checking access is not only more flexible, but it is also recognized by IDEA,
        so that, for example, you can know what roles you have in your project.
     */
    @PreAuthorize("hasRole('ROLE_BIG_BOSS')")
    public void verySecuredAnother() {
        System.out.println("Another very secured logic completed for " +
                SecurityContextHolder.getContext().getAuthentication().getName() +
                "!!!");
    }

}

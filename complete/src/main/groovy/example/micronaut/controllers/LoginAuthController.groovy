package example.micronaut.controllers

import example.micronaut.services.VelocityService
import groovy.transform.CompileStatic
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.security.Secured

@CompileStatic
@Secured("isAnonymous()") // <1>
@Controller("/login") // <2>
class LoginAuthController {

    protected final VelocityService velocityService

    LoginAuthController(VelocityService velocityService) { // <3>
        this.velocityService = velocityService
    }

    @Produces(MediaType.TEXT_HTML) // <4>
    @Get("/auth") // <5>
    String auth() {
        velocityService.render("auth.vm", [:]) // <6>
    }

    @Produces(MediaType.TEXT_HTML) // <4>
    @Get("/authFailed") // <7>
    String authFailed() {
        velocityService.render("auth.vm", authFailedModel()) // <6>
    }

    private Map<String, Object> authFailedModel() {
        [errors: true] as Map<String, Object>
    }
}
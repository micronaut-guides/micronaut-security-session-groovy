package example.micronaut.services

import groovy.transform.CompileStatic
import io.micronaut.security.authentication.AuthenticationFailed
import io.micronaut.security.authentication.AuthenticationProvider
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import io.micronaut.security.authentication.UserDetails
import io.reactivex.Flowable
import org.reactivestreams.Publisher
import javax.inject.Singleton


@CompileStatic
@Singleton // <1>
class AuthenticationProviderUserPassword implements AuthenticationProvider  { // <2>
    @Override
    Publisher<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
        if ( authenticationRequest.identity == "sherlock" && authenticationRequest.secret == "password" ) {
            UserDetails userDetails = new UserDetails((String) authenticationRequest.identity, new ArrayList<>())
            return Flowable.just(userDetails) as Flowable<AuthenticationResponse>
        }
        Flowable.just(new AuthenticationFailed()) as Flowable<AuthenticationResponse>
    }
}
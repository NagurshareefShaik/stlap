package shfl.st.lap.auditlog;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import shfl.st.lap.authentication.util.LoggedUserData;

public class AuditorAwareImpl implements AuditorAware<String> {
	
	@Autowired
	LoggedUserData loggedUserData;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(loggedUserData.getUserName());


    }
}
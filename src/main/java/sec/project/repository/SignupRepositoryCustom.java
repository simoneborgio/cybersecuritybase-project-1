package sec.project.repository;

import sec.project.domain.Signup;

public interface SignupRepositoryCustom {

    Integer insecureSave(Signup signup);

    Signup insecureFindOne(Long id);
}

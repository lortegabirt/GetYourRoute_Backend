package birt.eus.getyourroutebackend.validation;

import birt.eus.getyourroutebackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

  private final UserRepository userRepository;

  @Override
  public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
    return !userRepository.existsByEmail(email);
  }
}

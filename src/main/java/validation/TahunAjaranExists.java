package validation;

import entity.TahunAjaran;
import repo.TahunAjaranManajer;

import javax.inject.Inject;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TahunAjaranValidator.class)
public @interface TahunAjaranExists {
    String message() default "Tahun Ajaran tidak ditemukan";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

class TahunAjaranValidator implements ConstraintValidator<TahunAjaranExists, Long> {
    @Inject
    TahunAjaranManajer tahunAjaranManager;

    @Override
    public boolean isValid(Long tahunAjaranId, ConstraintValidatorContext context) {
        TahunAjaran tahunAjaran = tahunAjaranManager.findTahunAjaranById(tahunAjaranId);
        return tahunAjaran!=null;
    }
}

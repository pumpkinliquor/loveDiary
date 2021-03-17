package com.plushih.common.validation;

import com.plushih.common.utils.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternCheckValidator implements ConstraintValidator<PatternCheck, String> {

	private String pattern;

	@Override
	public void initialize(PatternCheck patternCheck) {
		this.pattern = patternCheck.pattern();
	}

	//회원정보 수정시에만 사용
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Pattern pattern = Pattern.compile(this.pattern);
		if (StringUtils.isEmpty(value)) {
			return true;
		} else {
			Matcher matcher = pattern.matcher(value);
			if (!matcher.find()) {
				return false;
			}
		}
		return true;
	}

}

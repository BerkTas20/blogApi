package com.berktas.blogApi.utils;

import com.berktas.blogApi.core.security.CustomUserDetails;
import com.berktas.blogApi.core.security.JwtTokenUtil;
import com.berktas.blogApi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class GeneralUtility {  //this test class used for generating random numbers

    private static List<Long> takenRandomNumbers = new ArrayList<>();
    private static Long nextUnique10DigitNumber = 0L;
    private static BigDecimal nextUnique26DigitNumber = BigDecimal.valueOf(0L);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public String getJwtToken(User user) {
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        return jwtTokenUtil.generate(customUserDetails, false);
    }

    public String getTimeStampStringOfNow() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
    }

    public Long getRandomUniqueNumberMaxTo(int max) {
        Long randomNumber = ((Double) Math.ceil(Math.random() * max)).longValue();
        while (takenRandomNumbers.contains(randomNumber)) {
            randomNumber = ((Double) Math.ceil(Math.random() * max)).longValue();
        }
        takenRandomNumbers.add(randomNumber);
        return randomNumber;
    }

    public Long getRandomNumberMaxTo(int max) {
        return ((Double) Math.ceil(Math.random() * max)).longValue();
    }

    // used for generating unique phone number
    public String getNextUnique10DigitNumberString() {
        Long nextNumber = ++nextUnique10DigitNumber;
        return IntStream.range(0, 10 - nextNumber.toString().length()).mapToObj(i -> "0")
                .collect(Collectors.joining(""))
                + nextNumber.toString();
    }

    // used for generating unique iban number
    public String getNextUnique26DigitNumberString() {
        nextUnique26DigitNumber.add(BigDecimal.ONE);
        BigDecimal nextNumber = nextUnique26DigitNumber;
        return IntStream.range(0, 26 - nextNumber.toString().length()).mapToObj(i -> "0")
                .collect(Collectors.joining(""))
                + nextNumber.toString();
    }
}

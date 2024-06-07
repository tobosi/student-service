package za.co.dsignweb.studentmanager.service.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface UniqueIdGenerator {

    Function<Integer, String> randomDigit = (size) -> {
        final Random random = new Random();
        return String.valueOf(random.nextInt((int) Math.pow(10, size)));
    };

    BiFunction<String, Integer, String> getCharacter = (data, index) -> {
        if (!StringUtils.isBlank(data)) {
            return String.valueOf(data.charAt(index)).toUpperCase();
        }
        return "";
    };
}

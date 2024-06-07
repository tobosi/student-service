package za.co.dsignweb.studentmanager.service.util;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

public enum PhoneValidationUtil {

    INSTANCE;

    public boolean isValid(final String cellphoneNumber) {
        try {
            final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
            final Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(
                    cellphoneNumber,
                    Phonenumber.PhoneNumber.CountryCodeSource.UNSPECIFIED.name()
            );

            return phoneNumberUtil.isValidNumber(phoneNumber);

        } catch (final Exception e) {
            return false;
        }
    }

}

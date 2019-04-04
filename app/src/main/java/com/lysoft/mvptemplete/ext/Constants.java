package com.lysoft.mvptemplete.ext;

public final class Constants {
    public static final String EMAIL_REQUIRED = "Email is required field.";
    public static final String PASSWORD_REQUIRED = "Password is required field.";
    public static final String CONFIRM_PASSWORD_REQUIRED = "Confirm Password is required field";
    public static final String CONFIRM_PASSWORD_NOT_MATCHED = "Confirm Password not matched";
    public static final String PASSWORD = "Password";
    public static final String EMAIL = "Email";

    //Constants for storage like preferences and databases
    public static class StorageConstants {
        public static final String PREFERENCE_NAME = "BaseAppPreferenceName";
        public static final String NAME = "ApplicationStorage";
        public static final String EMAIL = "pref_email";
        public static final String USER_ID = "user_id";
        public static final String PROFILE_PIC = "pref_pic";
        public static final String TEMP_PHOTO_FILE_NAME = "temp_photo.jpg";
        public static final String ACCESS_TOKEN = "access_token";

    }

    //Constants for server like api call
    public static class ServerConstants {
        //Base url, It is dummy base url, You have to change it to your working url
        public static final String BASE_URL = "http://www.yourbaseurl.com:2020/";

        //These are sample endpoints, you can change it according to your need
        public static final String LOGIN = "login";
        public static final String APP_VERSION = "api/getlatestappversion?platform=android";
        public static final String REGISTER = "api/register";
        public static final String FORGOT_PASSWORD = "api/forgotpassword";
        public static final String CHANGE_PROFILE_PIC = "api/uploadprofilepicture";
        public static final String DELETE_PROFILE_PIC = "api/removeprofilepicture";

    }

    //Constants for network usesed like network request time out
    public static class NetworkConstants {
        public static final int TIMEOUT = 25;
        public static final String HTTP_DIR_CACHE = "baseApp";
        public static final int CACHE_SIZE = 10 * 1024 * 1024;
    }
}

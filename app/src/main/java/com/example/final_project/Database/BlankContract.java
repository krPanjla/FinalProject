package com.example.final_project.Database;

import android.provider.BaseColumns;

public class BlankContract {

    public static class BlankEnter implements BaseColumns {

        public final static String _ID = BaseColumns._ID;
        public final static String _UID = BaseColumns._ID;

        public final static String LOGIN_TABLE_NAME = "finalUser_user";
        public final static String COLUMNS_USER_NAME = "name";
        public final static String COLUMNS_USER_EMAIL = "email";
        public final static String COLUMNS_USER_GENDER = "gender";
        public static final String COLUMNS_USER_PHONE = "phone";
        public final static String COLUMNS_USER_PASSWORD = "password";
        public final static String COLUMNS_USER_IMAGE = "image";

        public final static String BORROWER_TABLE_NAME = "borrower";
        public final static String COLUMNS_BORROWER_NAME = "name";
        public final static String COLUMNS_BORROWER_AMOUNT = "amount";
        public final static String COLUMNS_BORROWER_DATE = "date";
        public final static String COLUMNS_BORROWER_FLAG = "flag";

        /**
         * these variable defining the type of gender using in the application
         */
        public final static int GENDER_UNKNOWN = 0;
        public final static int GENDER_MALE = 1;
        public final static int GENDER_FEMALE = 2;
        public final static int GENDER_OTHER = 3;
    }
}

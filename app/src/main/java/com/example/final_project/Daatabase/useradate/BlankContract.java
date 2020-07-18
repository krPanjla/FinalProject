package com.example.final_project.Daatabase.useradate;

import android.provider.BaseColumns;

public class BlankContract {

    public static class BlankEnter implements BaseColumns {
        /**
         * @DATABASE_NAME : Database name
         */
        public final static String DATABASE_NAME = "FinalProject.db";

        public final static String _ID = BaseColumns._ID;
        public final static String LOGIN_TABLE_NAME = "finalUser_user";
        public final static String COLUMNS_USER_NAME = "name";
        public final static String COLUMNS_USER_GENDER = "gender";
        public static final String COLUMNS_USER_DOB = "dob";
        public static final String COLUMNS_USER_PHONE = "phone";
        public final static String COLUMNS_USER_PASSWORD = "password";
        public final static String COLUMNS_USER_IMAGE = "image";

        /**
         * these variable defining the type of gender using in the application
         */
        public final static int GENDER_UNKNOWN = 0;
        public final static int GENDER_MALE = 1;
        public final static int GENDER_FEMALE = 2;
        public final static int GENDER_OTHER = 3;
    }
}

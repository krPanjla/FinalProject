package com.example.final_project.Database.BorrowersDB;

import android.provider.BaseColumns;

public class BlankContact {
    //Template class to get the columns names ,table name and database name.
    public static class BlankEnter implements BaseColumns {
        /**
         * @DATABASE_NAME : Database name
         */
        public final static String DATABASE_NAME = "finalProject.db";

        public final static String BORROWER_TABLE_NAME = "borrower";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMNS_BORROWER_NAME = "name";
        public final static String COLUMNS_BORROWER_AMOUNT = "amount";
        public final static String COLUMNS_BORROWER_DATE = "date";
        public final static String COLUMNS_BORROWER_FLAG = "flag";
    }
}

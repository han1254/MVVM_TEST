package com.example.mvvm_test.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;

import com.example.mvvm_test.bussiness.test.mvvm.model.Person;


@Database(entities = {Person.class}, version = 1,exportSchema = false)
public abstract class PersonDataBase extends RoomDatabase {

    public abstract PersonDao getPersonDao();


    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `person_list2` (`person_age` INTEGER, `person_name` TEXT,"
                    + "`person_id` INTEGER, PRIMARY KEY(`person_id`))");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Book "
                    + " ADD COLUMN pub_year INTEGER");
        }
    };

}

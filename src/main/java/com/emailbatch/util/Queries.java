package com.emailbatch.util;

public interface Queries {
    String GET_EMAILS_FOR_MAILING = "select userTable.email\n" +
            "from public.users as userTable\n" +
            "         left outer join public.notifications as notificationTable\n" +
            "                         on userTable.id = notificationTable.user_id\n" +
            "where notificationTable.email = true";
}

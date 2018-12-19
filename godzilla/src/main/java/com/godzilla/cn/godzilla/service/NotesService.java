package com.godzilla.cn.godzilla.service;

import com.godzilla.cn.godzilla.bean.Notes;
import com.godzilla.cn.godzilla.bean.User;

import java.sql.Date;
import java.util.List;


public interface NotesService {
    int add(long userId,String note, String content, String date);
    int update( String note, String content, long id);
    int delete(long id);
    Notes findNotesById(long id);
    List<Notes> findNotesByUserId(long userId);
    List<Notes> findInfoByUserId(long userId);


}

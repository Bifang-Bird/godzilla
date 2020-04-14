package com.godzilla.cn.godzilla.service;

import com.godzilla.cn.godzilla.bean.Notes;
import java.util.List;


public interface NotesService {
    int add(long userId,String note, String content, String date,String state);
    int update( String note, String content,String date,String state, long id);
    int delete(long id);
    Notes findNotesById(long id);
    Notes findLastNotes(String note);
    List<Notes> findNotesByUserId(long userId);
    List<Notes> findInfoByUserId(long userId);


}

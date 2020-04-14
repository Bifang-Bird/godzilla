package com.godzilla.cn.godzilla.service.serviceImp;

import com.godzilla.cn.godzilla.bean.Notes;
import com.godzilla.cn.godzilla.mapper.NotesMapper;
import com.godzilla.cn.godzilla.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesServiceImpl implements NotesService {

    @Autowired
    private NotesMapper notesMapper;

    @Override
    public int add(long userId,String note, String content, String date,String state) {
        return notesMapper.add(userId,note,content,date,state);
    }

    @Override
    public int update(String note, String content,String date,String state, long id) {
        return notesMapper.update(note,content,date,state,id);
    }

    @Override
    public int delete(long id) {
        return notesMapper.delete(id);
    }

    @Override
    public Notes findNotesById(long id) {
        return notesMapper.findNotesById(id);
    }

    @Override
    public Notes findLastNotes(String note) {
        return notesMapper.findLastNotes(note);
    }

    @Override
    public List<Notes> findNotesByUserId(long id) {
        return notesMapper.findNotesByUserId(id);
    }

    @Override
    public List<Notes> findInfoByUserId(long userId) {
        return notesMapper.findInfoByUserId(userId);
    }

}

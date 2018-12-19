package com.godzilla.cn.godzilla.service.serviceImp;

import com.godzilla.cn.godzilla.bean.Notes;
import com.godzilla.cn.godzilla.bean.User;
import com.godzilla.cn.godzilla.mapper.NotesMapper;
import com.godzilla.cn.godzilla.mapper.UserMapper;
import com.godzilla.cn.godzilla.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class NotesServiceImpl implements NotesService {

    @Autowired
    private NotesMapper notesMapper;

    @Override
    public int add(long userId,String note, String content, String date) {
        return notesMapper.add(userId,note,content,date);
    }

    @Override
    public int update(String note, String content, long id) {
        return notesMapper.update(note,content,id);
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
    public List<Notes> findNotesByUserId(long id) {
        return notesMapper.findNotesByUserId(id);
    }

    @Override
    public List<Notes> findInfoByUserId(long userId) {
        return notesMapper.findInfoByUserId(userId);
    }

}

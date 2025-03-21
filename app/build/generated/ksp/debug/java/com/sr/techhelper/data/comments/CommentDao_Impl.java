package com.sr.techhelper.data.comments;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.sr.techhelper.data.users.UserModel;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CommentDao_Impl implements CommentDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CommentModel> __insertionAdapterOfCommentModel;

  private final EntityDeletionOrUpdateAdapter<CommentModel> __deletionAdapterOfCommentModel;

  public CommentDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCommentModel = new EntityInsertionAdapter<CommentModel>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `comments` (`id`,`postId`,`userId`,`content`,`timestamp`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CommentModel entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getPostId());
        statement.bindString(3, entity.getUserId());
        statement.bindString(4, entity.getContent());
        statement.bindLong(5, entity.getTimestamp());
      }
    };
    this.__deletionAdapterOfCommentModel = new EntityDeletionOrUpdateAdapter<CommentModel>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `comments` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CommentModel entity) {
        statement.bindString(1, entity.getId());
      }
    };
  }

  @Override
  public void add(final CommentModel... comment) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCommentModel.insert(comment);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final CommentModel comment) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfCommentModel.handle(comment);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<CommentWithSender>> getAllComments() {
    final String _sql = "SELECT * FROM comments";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"users",
        "comments"}, false, new Callable<List<CommentWithSender>>() {
      @Override
      @Nullable
      public List<CommentWithSender> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPostId = CursorUtil.getColumnIndexOrThrow(_cursor, "postId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final ArrayMap<String, UserModel> _collectionSender = new ArrayMap<String, UserModel>();
          while (_cursor.moveToNext()) {
            final String _tmpKey;
            _tmpKey = _cursor.getString(_cursorIndexOfUserId);
            _collectionSender.put(_tmpKey, null);
          }
          _cursor.moveToPosition(-1);
          __fetchRelationshipusersAscomSrTechhelperDataUsersUserModel(_collectionSender);
          final List<CommentWithSender> _result = new ArrayList<CommentWithSender>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CommentWithSender _item;
            final CommentModel _tmpComment;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpPostId;
            _tmpPostId = _cursor.getString(_cursorIndexOfPostId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _tmpComment = new CommentModel(_tmpId,_tmpPostId,_tmpUserId,_tmpContent,_tmpTimestamp);
            final UserModel _tmpSender;
            final String _tmpKey_1;
            _tmpKey_1 = _cursor.getString(_cursorIndexOfUserId);
            _tmpSender = _collectionSender.get(_tmpKey_1);
            _item = new CommentWithSender(_tmpComment,_tmpSender);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshipusersAscomSrTechhelperDataUsersUserModel(
      @NonNull final ArrayMap<String, UserModel> _map) {
    final Set<String> __mapKeySet = _map.keySet();
    if (__mapKeySet.isEmpty()) {
      return;
    }
    if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      RelationUtil.recursiveFetchArrayMap(_map, false, (map) -> {
        __fetchRelationshipusersAscomSrTechhelperDataUsersUserModel(map);
        return Unit.INSTANCE;
      });
      return;
    }
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`name`,`email`,`profile_picture` FROM `users` WHERE `id` IN (");
    final int _inputSize = __mapKeySet.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (String _item : __mapKeySet) {
      _stmt.bindString(_argIndex, _item);
      _argIndex++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "id");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = 0;
      final int _cursorIndexOfName = 1;
      final int _cursorIndexOfEmail = 2;
      final int _cursorIndexOfProfilePicture = 3;
      while (_cursor.moveToNext()) {
        final String _tmpKey;
        _tmpKey = _cursor.getString(_itemKeyIndex);
        if (_map.containsKey(_tmpKey)) {
          final UserModel _item_1;
          final String _tmpId;
          _tmpId = _cursor.getString(_cursorIndexOfId);
          final String _tmpName;
          _tmpName = _cursor.getString(_cursorIndexOfName);
          final String _tmpEmail;
          _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
          final String _tmpProfile_picture;
          _tmpProfile_picture = _cursor.getString(_cursorIndexOfProfilePicture);
          _item_1 = new UserModel(_tmpId,_tmpName,_tmpEmail,_tmpProfile_picture);
          _map.put(_tmpKey, _item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}

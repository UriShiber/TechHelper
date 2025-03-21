package com.sr.techhelper.data.posts;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.EntityUpsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
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
public final class PostDao_Impl implements PostDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PostModel> __insertionAdapterOfPostModel;

  private final EntityDeletionOrUpdateAdapter<PostModel> __deletionAdapterOfPostModel;

  private final EntityDeletionOrUpdateAdapter<PostModel> __updateAdapterOfPostModel;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final EntityUpsertionAdapter<PostModel> __upsertionAdapterOfPostModel;

  public PostDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPostModel = new EntityInsertionAdapter<PostModel>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `posts` (`id`,`title`,`description`,`userId`,`locationLng`,`locationLat`,`image`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PostModel entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getDescription());
        statement.bindString(4, entity.getUserId());
        statement.bindDouble(5, entity.getLocationLng());
        statement.bindDouble(6, entity.getLocationLat());
        if (entity.getImage() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getImage());
        }
      }
    };
    this.__deletionAdapterOfPostModel = new EntityDeletionOrUpdateAdapter<PostModel>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `posts` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PostModel entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfPostModel = new EntityDeletionOrUpdateAdapter<PostModel>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `posts` SET `id` = ?,`title` = ?,`description` = ?,`userId` = ?,`locationLng` = ?,`locationLat` = ?,`image` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PostModel entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getDescription());
        statement.bindString(4, entity.getUserId());
        statement.bindDouble(5, entity.getLocationLng());
        statement.bindDouble(6, entity.getLocationLat());
        if (entity.getImage() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getImage());
        }
        statement.bindString(8, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM posts WHERE id = ?";
        return _query;
      }
    };
    this.__upsertionAdapterOfPostModel = new EntityUpsertionAdapter<PostModel>(new EntityInsertionAdapter<PostModel>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `posts` (`id`,`title`,`description`,`userId`,`locationLng`,`locationLat`,`image`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PostModel entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getDescription());
        statement.bindString(4, entity.getUserId());
        statement.bindDouble(5, entity.getLocationLng());
        statement.bindDouble(6, entity.getLocationLat());
        if (entity.getImage() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getImage());
        }
      }
    }, new EntityDeletionOrUpdateAdapter<PostModel>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `posts` SET `id` = ?,`title` = ?,`description` = ?,`userId` = ?,`locationLng` = ?,`locationLat` = ?,`image` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PostModel entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getDescription());
        statement.bindString(4, entity.getUserId());
        statement.bindDouble(5, entity.getLocationLng());
        statement.bindDouble(6, entity.getLocationLat());
        if (entity.getImage() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getImage());
        }
        statement.bindString(8, entity.getId());
      }
    });
  }

  @Override
  public void add(final PostModel... post) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPostModel.insert(post);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final PostModel post) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfPostModel.handle(post);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final PostModel post) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfPostModel.handle(post);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteById(final String id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
    int _argIndex = 1;
    _stmt.bindString(_argIndex, id);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteById.release(_stmt);
    }
  }

  @Override
  public void upsertAll(final PostModel... post) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __upsertionAdapterOfPostModel.upsert(post);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<PostWithSender>> getAllPosts() {
    final String _sql = "SELECT * FROM posts";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"users",
        "posts"}, false, new Callable<List<PostWithSender>>() {
      @Override
      @Nullable
      public List<PostWithSender> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfLocationLng = CursorUtil.getColumnIndexOrThrow(_cursor, "locationLng");
          final int _cursorIndexOfLocationLat = CursorUtil.getColumnIndexOrThrow(_cursor, "locationLat");
          final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(_cursor, "image");
          final ArrayMap<String, UserModel> _collectionSender = new ArrayMap<String, UserModel>();
          while (_cursor.moveToNext()) {
            final String _tmpKey;
            _tmpKey = _cursor.getString(_cursorIndexOfUserId);
            _collectionSender.put(_tmpKey, null);
          }
          _cursor.moveToPosition(-1);
          __fetchRelationshipusersAscomSrTechhelperDataUsersUserModel(_collectionSender);
          final List<PostWithSender> _result = new ArrayList<PostWithSender>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PostWithSender _item;
            final PostModel _tmpPost;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final double _tmpLocationLng;
            _tmpLocationLng = _cursor.getDouble(_cursorIndexOfLocationLng);
            final double _tmpLocationLat;
            _tmpLocationLat = _cursor.getDouble(_cursorIndexOfLocationLat);
            final String _tmpImage;
            if (_cursor.isNull(_cursorIndexOfImage)) {
              _tmpImage = null;
            } else {
              _tmpImage = _cursor.getString(_cursorIndexOfImage);
            }
            _tmpPost = new PostModel(_tmpId,_tmpTitle,_tmpDescription,_tmpUserId,_tmpLocationLng,_tmpLocationLat,_tmpImage);
            final UserModel _tmpSender;
            final String _tmpKey_1;
            _tmpKey_1 = _cursor.getString(_cursorIndexOfUserId);
            _tmpSender = _collectionSender.get(_tmpKey_1);
            _item = new PostWithSender(_tmpPost,_tmpSender);
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

  @Override
  public LiveData<PostWithSender> getById(final String id) {
    final String _sql = "SELECT * FROM posts WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    return __db.getInvalidationTracker().createLiveData(new String[] {"users",
        "posts"}, false, new Callable<PostWithSender>() {
      @Override
      @Nullable
      public PostWithSender call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfLocationLng = CursorUtil.getColumnIndexOrThrow(_cursor, "locationLng");
          final int _cursorIndexOfLocationLat = CursorUtil.getColumnIndexOrThrow(_cursor, "locationLat");
          final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(_cursor, "image");
          final ArrayMap<String, UserModel> _collectionSender = new ArrayMap<String, UserModel>();
          while (_cursor.moveToNext()) {
            final String _tmpKey;
            _tmpKey = _cursor.getString(_cursorIndexOfUserId);
            _collectionSender.put(_tmpKey, null);
          }
          _cursor.moveToPosition(-1);
          __fetchRelationshipusersAscomSrTechhelperDataUsersUserModel(_collectionSender);
          final PostWithSender _result;
          if (_cursor.moveToFirst()) {
            final PostModel _tmpPost;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final double _tmpLocationLng;
            _tmpLocationLng = _cursor.getDouble(_cursorIndexOfLocationLng);
            final double _tmpLocationLat;
            _tmpLocationLat = _cursor.getDouble(_cursorIndexOfLocationLat);
            final String _tmpImage;
            if (_cursor.isNull(_cursorIndexOfImage)) {
              _tmpImage = null;
            } else {
              _tmpImage = _cursor.getString(_cursorIndexOfImage);
            }
            _tmpPost = new PostModel(_tmpId,_tmpTitle,_tmpDescription,_tmpUserId,_tmpLocationLng,_tmpLocationLat,_tmpImage);
            final UserModel _tmpSender;
            final String _tmpKey_1;
            _tmpKey_1 = _cursor.getString(_cursorIndexOfUserId);
            _tmpSender = _collectionSender.get(_tmpKey_1);
            _result = new PostWithSender(_tmpPost,_tmpSender);
          } else {
            _result = null;
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

  @Override
  public LiveData<List<PostWithSender>> getPostsByUserId(final String userId) {
    final String _sql = "SELECT * FROM posts WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"users",
        "posts"}, false, new Callable<List<PostWithSender>>() {
      @Override
      @Nullable
      public List<PostWithSender> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfLocationLng = CursorUtil.getColumnIndexOrThrow(_cursor, "locationLng");
          final int _cursorIndexOfLocationLat = CursorUtil.getColumnIndexOrThrow(_cursor, "locationLat");
          final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(_cursor, "image");
          final ArrayMap<String, UserModel> _collectionSender = new ArrayMap<String, UserModel>();
          while (_cursor.moveToNext()) {
            final String _tmpKey;
            _tmpKey = _cursor.getString(_cursorIndexOfUserId);
            _collectionSender.put(_tmpKey, null);
          }
          _cursor.moveToPosition(-1);
          __fetchRelationshipusersAscomSrTechhelperDataUsersUserModel(_collectionSender);
          final List<PostWithSender> _result = new ArrayList<PostWithSender>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PostWithSender _item;
            final PostModel _tmpPost;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final double _tmpLocationLng;
            _tmpLocationLng = _cursor.getDouble(_cursorIndexOfLocationLng);
            final double _tmpLocationLat;
            _tmpLocationLat = _cursor.getDouble(_cursorIndexOfLocationLat);
            final String _tmpImage;
            if (_cursor.isNull(_cursorIndexOfImage)) {
              _tmpImage = null;
            } else {
              _tmpImage = _cursor.getString(_cursorIndexOfImage);
            }
            _tmpPost = new PostModel(_tmpId,_tmpTitle,_tmpDescription,_tmpUserId,_tmpLocationLng,_tmpLocationLat,_tmpImage);
            final UserModel _tmpSender;
            final String _tmpKey_1;
            _tmpKey_1 = _cursor.getString(_cursorIndexOfUserId);
            _tmpSender = _collectionSender.get(_tmpKey_1);
            _item = new PostWithSender(_tmpPost,_tmpSender);
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

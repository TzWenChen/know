package sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.knowmemo.usermanagement.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 2016/1/30.
 */
public class tableDao {

    public static String user_id = "user_test";
    public static final String words = "words";
    public static final String meaning = "meaning";
    public static final String exp = "exp";
    public static final String categories = "categories";
    public static final String favorites = "favorites";
    public static int box_level_1_Limit = 10;
    public static int box_level_2_Limit = 20;
    public static int box_level_3_Limit = 40;
    public static int box_level_4_Limit = 80;
    public static int box_level_5_Limit = 160;
    private SQLiteDatabase db;

    public static final String createWordsTable = "CREATE TABLE IF NOT EXISTS " + words
            + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "word VARCHAR(30) NOT NULL,"
            + "phonetics VARCHAR(30),"
            + "GEPTlow INTEGER(1) NOT NULL,"
            + "GEPTmiddle INTEGER(1) NOT NULL,"
            + "GEPTmiddlehigh INTEGER(1) NOT NULL,"
            + "GEPThigh INTEGER(1) NOT NULL,"
            + "TOEFL INTEGER(1) NOT NULL,"
            + "TOEIC INTEGER(1) NOT NULL,"
            + "IELTS INTEGER(1) NOT NULL)";

    public static final String createMeaningTable = "CREATE TABLE IF NOT EXISTS " + meaning
            + "(sub_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "id INTEGER NOT NULL,"
            + "word VARCHAR(30) NOT NULL,"
            + "part_of_speech VARCHAR(4) ,"
            + "EngChiTra VARCHAR(100) NOT NULL,"
            + "EngEng VARCHAR(100),"
            + "synonym VARCHAR(30),"
            + "antonym VARCHAR(30),"
            + "sentence VARCHAR(100),"
            + "EngChiSpl VARCHAR(100),"
            + "EngJp VARCHAR(100),"
            + "EngKorea VARCHAR(100))";


    public static final String createExpTable = "CREATE TABLE IF NOT EXISTS " + exp
            + " (user_id VARCHAR NOT NULL,"
            + "word_id INTEGER PRIMARY KEY,"
            + "level INTEGER(3) NOT NULL,"
            + "position INTEGER(5) NOT NULL,"
            + "learned INTEGER(5),"
            + "Last_Learnt_Time VARCHAR(20))";

    public static final String createCategoriesTable = "CREATE TABLE IF NOT EXISTS " + categories
            + " (sub_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "id INTEGER(10) NOT NULL,"
            + "word VARCHAR(20) NOT NULL,"
            + "catagory INTEGER(11) NOT NULL)";

    public static final String createFavoritesTable = "CREATE TABLE IF NOT EXISTS " + favorites
            + " (favor_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "favor_word VARCHAR(20) NOT NULL,"
            + "favor_meaning VARCHAR(20) NOT NULL)";


    // 建構子，一般的應用都不需要修改
    public tableDao(Context context) {
        db = SqlHelper.getDatabase(context);
    }

    // 關閉資料庫，一般的應用都不需要修改
    public void close() {
        db.close();
    }


    /*****
     * 跟　favorites相關的所有SQL語法
     *****/


    public boolean deleteFavorItem(String theWord) {

        String where = "favor_word" +  "=\"" + theWord + "\"";

        // 刪除指定資料並回傳刪除是否成功
        return db.delete(favorites, where, null) > 0;
    }
    public int getFavorsCount() {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + favorites, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        cursor.close();
        return result;
    }
    public void insertFavorites(String favorWord, String meaning ) {
        // 建立準備新增資料的ContentValues物件
        ContentValues cv = new ContentValues();
            // 加入ContentValues物件包裝的新增資料
            // 第一個參數是欄位名稱， 第二個參數是欄位的資料
            cv.put("favor_word", favorWord);
            cv.put("favor_meaning", meaning);

            // 第一個參數是表格名稱
            // 第二個參數是沒有指定欄位值的預設值
            // 第三個參數是包裝新增資料的ContentValues物件
            db.insert(favorites, null, cv);

    }
    public boolean getFavoritesbyWord(String theWord) {
        boolean result =true;
        Cursor cursor = null;

        String where = "favor_word" +  "=\"" + theWord + "\"";
        // 執行查詢
        cursor = db.query(
                favorites, null, where, null, null, null, null, null);

        if (!cursor.moveToFirst()) {
            result = false;
        }

        // 關閉Cursor物件
        cursor.close();
        // 回傳結果
        return result;
    }
    public ArrayList<HashMap<String,Object >> getFavoritesWords() {
        Cursor cursor = null;
        ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String,Object>>();
        // 執行查詢
        cursor = db.query(
                favorites, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("單字",cursor.getString(1));
            item.put("解釋",cursor.getString(2));
            item.put("按鈕", R.drawable.trash);
            result.add(item);
        }
        // 關閉Cursor物件
        cursor.close();
        // 回傳結果
        return result;
    }

    /*****
     * 跟word相關的所有SQL語法
     *****/

    // 新增Words物件
    public void insertWords(sqlite.Words addWords) {
        // 建立準備新增資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的新增資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put("id", addWords.getId());
        cv.put("word", addWords.getWord());
        cv.put("phonetics", addWords.getPhonetics());
        cv.put("GEPTlow", addWords.getGEPTlow());
        cv.put("GEPTmiddle", addWords.getGEPTmiddle());
        cv.put("GEPTmiddlehigh", addWords.getGEPTmiddlehigh());
        cv.put("GEPThigh", addWords.getGEPThigh());
        cv.put("TOEFL", addWords.getTOEFL());
        cv.put("TOEIC", addWords.getTOEIC());
        cv.put("IELTS", addWords.getIELTS());

        // 第一個參數是表格名稱
        // 第二個參數是沒有指定欄位值的預設值
        // 第三個參數是包裝新增資料的ContentValues物件
        db.insert(words, null, cv);
    }

//    // 讀取所有words記事資料
//    public List<sqlite.Words> getAllWords() {
//        List<sqlite.Words> result = new ArrayList<>();
//        Cursor cursor = db.query(
//                words, null, null, null, null, null, null, null);
//
//        while (cursor.moveToNext()) {
//            result.add(getWordsRecord(cursor));
//        }
//
//        cursor.close();
//        return result;
//    }

//    // 讀取相對應的words記事資料
//    public List<sqlite.Words> getWords(String wordsType) {
//        List<sqlite.Words> result = new ArrayList<>();
//        String where = "TOEIC" + "= " + "1";
//        Cursor cursor = db.query(
//                words, null, where, null, null, null, null, null);
//
//        while (cursor.moveToNext()) {
//            result.add(getWordsRecord(cursor));
//        }
//
//        cursor.close();
//        return result;
//    }

    public boolean getWordsByWord(String theWord) {
        // 準備回傳結果用的物件
        boolean result =true;
        Cursor cursor = null;

        String where = "word" + "=" + theWord;
        // 執行查詢
        cursor = db.query(
                words, null, where, null, null, null, null, null);

        while (!cursor.moveToFirst()) {
            result = false;
        }

        // 關閉Cursor物件
        cursor.close();
        // 回傳結果
        return result;
    }
    public List<sqlite.Words> getWordsById(List<Exp> expReturnList) {
        // 準備回傳結果用的物件
        List<sqlite.Words> result = new ArrayList<>();
        Cursor cursor = null;
        for (int i = 0; i < expReturnList.size(); i++) {
            // 使用編號為查詢條件
            String where = "id" + "=" + expReturnList.get(i).getWord_id();
            // 執行查詢
            cursor = db.query(
                    words, null, where, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                result.add(getWordsRecord(cursor));
            }
        }
        // 關閉Cursor物件
        cursor.close();
        // 回傳結果
        return result;
    }

    // 把Cursor目前的資料包裝為Words物件
    public sqlite.Words getWordsRecord(Cursor cursor) {
        // 準備回傳結果用的物件
        sqlite.Words result = new sqlite.Words();

        result.setId(cursor.getInt(0));
        result.setWord(cursor.getString(1));
        result.setPhonetics(cursor.getString(2));
        result.setGEPTlow(cursor.getInt(3));
        result.setGEPTmiddle(cursor.getInt(4));
        result.setGEPTmiddlehigh(cursor.getInt(5));
        result.setGEPThigh(cursor.getInt(6));
        result.setTOEFL(cursor.getInt(7));
        result.setTOEIC(cursor.getInt(8));
        result.setIELTS(cursor.getInt(9));

        // 回傳結果
        return result;
    }

    // 刪除words的資料
    public boolean deleteWords() {
        // 刪除指定編號資料並回傳刪除是否成功
        return db.delete(words, null, null) > 0;
    }


//    public List<sqlite.Words> topSomeWords(int max) {
//        List<sqlite.Words> result = new ArrayList<>();
//        System.out.println("going to query Some data");
//        Cursor cursor;
//        int newmax = getExpCount()-20;
//        String query = "";
//        query = "SELECT * FROM words ORDER BY id ASC limit " + max +","+ newmax; //max+1~~max+1+10    21~30
//
//        cursor = db.rawQuery(query, null);
//        System.out.println("query = " + query);
//        while (cursor.moveToNext()) {
//            result.add(getWordsRecord(cursor));
//        }
//        for (int i = 0; i < newmax; i++) {
//            Exp expAdd = new Exp(user_id, result.get(i).getId(), 1, i, 1, "");
//            this.insertExp(expAdd);
//        }
//        cursor.close();
//        return result;
//    }
    //選擇10張未學習過的卡片，新增至Exp table讓使用者學習
    public List<sqlite.Words> top10Words(int max) {
        List<sqlite.Words> result = new ArrayList<>();
        System.out.println("going to query 10 data");
        Cursor cursor;
        String query = "";
        if (max == 0) {
            query = "SELECT * FROM words ORDER BY id ASC limit 0,10";

        } else {
            query = "SELECT * FROM words ORDER BY id ASC limit " + max + ",10"; //max+1~~max+1+10    21~30
        }
        cursor = db.rawQuery(query, null);
        System.out.println("query = " + query);
        while (cursor.moveToNext()) {
            result.add(getWordsRecord(cursor));
        }
        for (int i = 0; i < 10; i++) {
            Exp expAdd = new Exp(user_id, result.get(i).getId(), 1, i, 1, "");
            this.insertExp(expAdd);
        }
        cursor.close();
        return result;
    }



//    // 取得words資料數量
//    public int getWordsCount() {
//        int result = 0;
//        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + words, null);
//
//        if (cursor.moveToNext()) {
//            result = cursor.getInt(0);
//        }
//
//        cursor.close();
//        return result;
//    }

//    // 刪除參數指定編號的資料
//    public boolean deleteWords(int id) {
//        // 設定條件為編號，格式為「欄位名稱=資料」
//        String where = id + "=" + id;
//        // 刪除指定編號資料並回傳刪除是否成功
//        return db.delete(words, where, null) > 0;
//    }



    /*****
     * 跟meaning相關的SQL語法
     *****/

    // 新增Meaning物件
    public void insertMeaning(Meaning addMeaning) {
        // 建立準備新增資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的新增資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put("sub_id", addMeaning.getSub_id());
        cv.put("id", addMeaning.getId());
        cv.put("word", addMeaning.getWord());
        cv.put("part_of_speech", addMeaning.getPart_of_speech());
        cv.put("EngChiTra", addMeaning.getEngChiTra());
        cv.put("EngEng", addMeaning.getEngEng());
        cv.put("synonym", addMeaning.getSynonym());
        cv.put("antonym", addMeaning.getAntonym());
        cv.put("sentence", addMeaning.getSentence());
        cv.put("EngChiSpl", addMeaning.getEngChiSpl());
        cv.put("EngJp", addMeaning.getEngJp());
        cv.put("EngKorea", addMeaning.getEngKorea());

        // 第一個參數是表格名稱
        // 第二個參數是沒有指定欄位值的預設值
        // 第三個參數是包裝新增資料的ContentValues物件
        db.insert(meaning, null, cv);
    }

    //透過ID搜尋meaning
    public List<Meaning> getMeaningById(int id) {
        // 準備回傳結果用的物件
        List<Meaning> result = new ArrayList<>();
        // 使用編號為查詢條件
        String where = "id" + "=" + id;
        // 執行查詢
        Cursor cursor = db.query(
                meaning, null, where, null, null, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getMeaningRecord(cursor));
        }

        // 關閉Cursor物件
        cursor.close();
        // 回傳結果
        return result;
    }

    // 把Cursor目前的資料包裝為Meaning物件
    public Meaning getMeaningRecord(Cursor cursor) {
        // 準備回傳結果用的物件
        Meaning result = new Meaning();

        result.setSub_id(cursor.getInt(0));
        result.setId(cursor.getInt(1));
        result.setWord(cursor.getString(2));
        result.setPart_of_speech(cursor.getString(3));
        result.setEngChiTra(cursor.getString(4));
        result.setEngEng(cursor.getString(5));
        result.setSynonym(cursor.getString(6));
        result.setAntonym(cursor.getString(7));
        result.setSentence(cursor.getString(8));
        result.setEngChiSpl(cursor.getString(9));
        result.setEngJp(cursor.getString(10));
        result.setEngKorea(cursor.getString(11));

        // 回傳結果
        return result;
    }
    // 刪除meaning的資料
    public boolean deleteMeaning() {
        // 刪除指定編號資料並回傳刪除是否成功
        return db.delete(meaning, null, null) > 0;
    }


//    // 取得meaning資料數量
//    public int getMeaningCount() {
//        int result = 0;
//        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + meaning, null);
//
//        if (cursor.moveToNext()) {
//            result = cursor.getInt(0);
//        }
//        cursor.close();
//        return result;
//    }


    /*****
     * 跟exp相關的SQL語法
     *****/




    // 新增Exp物件
    public void insertExp(Exp addExp) {
        // 建立準備新增資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的新增資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put("user_id", addExp.getUser_id());
        cv.put("word_id", addExp.getWord_id());
        cv.put("level", addExp.getLevel());
        cv.put("position", addExp.getPosition());
        cv.put("learned", addExp.getLearned());
        cv.put("Last_Learnt_Time", addExp.getLast_Learnt_Time());

        // 第一個參數是表格名稱
        // 第二個參數是沒有指定欄位值的預設值
        // 第三個參數是包裝新增資料的ContentValues物件
        db.insert(exp, null, cv);
    }

    // 取得exp資料數量
    public int getExpCount() {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + exp, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }
        cursor.close();
        return result;
    }

    // 取得exp中word_id最大為多少
    public int getExpMaxWordId() {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT Max(word_id) FROM " + exp, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }
        System.out.println("exp max word_id :" + result);
        cursor.close();
        return result;
    }

    // 刪除參數指定編號的資料
    public boolean deleteExp() {
        // 刪除指定編號資料並回傳刪除是否成功
        return db.delete(exp, null, null) > 0;
    }

    public Exp getExpById(int id) {
        // 準備回傳結果用的物件
        Exp result = new Exp();
        // 使用編號為查詢條件
        String where = "word_id" + "=" + id;
        // 執行查詢
        Cursor cursor = db.query(
                exp, null, where, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            result = getExpRecord(cursor);
        }

        // 關閉Cursor物件
        cursor.close();
        // 回傳結果
        return result;
    }

    // 把Cursor目前的資料包裝為Exp物件
    public Exp getExpRecord(Cursor cursor) {
        // 準備回傳結果用的物件
        Exp result = new Exp();

        result.setUser_id(cursor.getString(0));
        result.setWord_id(cursor.getInt(1));
        result.setLevel(cursor.getInt(2));
        result.setPosition(cursor.getInt(3));
        result.setLearned(cursor.getInt(4));
        result.setLast_Learnt_Time(cursor.getString(5));

        // 回傳結果
        return result;
    }

    // 取得exp資料數量
    public int getBoxCount(int level) {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM exp WHERE level=" + level + "", null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }
        cursor.close();
        return result;
    }

    //取得箱子內該level的卡片
    public List<Exp> boxLevelData(int level) {
        // 準備回傳結果用的物件
        List<Exp> result = new ArrayList<>();
        // 使用編號為查詢條件
        String where = " level = " + level;
        // 執行查詢
        Cursor cursor = db.query(
                exp, null, where, null, null, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getExpRecord(cursor));
        }

        // 關閉Cursor物件
        cursor.close();
        // 回傳結果
        return result;
    }

    //確認要前往的箱子單字數
    public boolean checkBoxCount(int level){
        int count = this.getBoxCount(level)+1;
        System.out.println("method count : " + count);
        boolean insertOk = false;

        if(level==1) {
            if (count < box_level_1_Limit)
                insertOk = true;
            else
                insertOk = false;
        }else if(level==2) {
            if (count < box_level_2_Limit)
                insertOk = true;
            else
                insertOk = false;
        } else if(level==3) {
            if (count < box_level_3_Limit)
                insertOk = true;
            else
                insertOk = false;
        } else if(level==4) {
            if (count < box_level_4_Limit)
                insertOk = true;
            else
                insertOk = false;
        }else if(level==5) {
            if (count < box_level_5_Limit)
                insertOk = true;
            else
                insertOk = false;
        }
        return insertOk;
    }

    // 修改參數指定的物件
    public void updateExp(Exp expUpdate) {
        // 建立準備修改資料的ContentValues物件
        ContentValues cv = new ContentValues();

        cv.put("level", expUpdate.getLevel());
        cv.put("position", expUpdate.getPosition());
        cv.put("learned", expUpdate.getLearned());
        cv.put("Last_Learnt_Time", expUpdate.getLast_Learnt_Time());

        // 設定修改資料的條件為編號
        // 格式為「欄位名稱＝資料」
        String where = "word_id =" + expUpdate.getWord_id();

        // 執行修改資料並回傳修改的資料數量是否成功
        db.update(exp, cv, where, null);
    }

    public void sampleWord() {
        String data = "1,affair,,0,0,0,0,0,1,0\n" +
                "2,affluent,,0,0,0,0,0,1,0\n" +
                "3,attain,,0,0,0,0,0,1,0\n" +
                "4,authentic,,0,0,0,0,0,1,0\n" +
                "5,audit,,0,0,0,0,0,1,0\n" +
                "6,auction,,0,0,0,0,0,1,0\n" +
                "7,barter,,0,0,0,0,0,1,0\n" +
                "8,boost,,0,0,0,0,0,1,0\n" +
                "9,cargo,,0,0,0,0,0,1,0\n" +
                "10,circumstance,,0,0,0,0,0,1,0\n" +
                "11,consignment,,0,0,0,0,0,1,0\n" +
                "12,counsel,,0,0,0,0,0,1,0\n" +
                "13,diversification,,0,0,0,0,0,1,0\n" +
                "14,expiration,,0,0,0,0,0,1,0\n" +
                "15,exploitation,,0,0,0,0,0,1,0\n" +
                "16,exemption,,0,0,0,0,0,1,0\n" +
                "17,flourish,,0,0,0,0,0,1,0\n" +
                "18,freight,,0,0,0,0,0,1,0\n" +
                "19,genuine,,0,0,0,0,0,1,0\n" +
                "20,grant,,0,0,0,0,0,1,0\n" +
                "21,imposition,,0,0,0,0,0,1,0\n" +
                "22,inevitable,,0,0,0,0,0,1,0\n" +
                "23,lease,,0,0,0,0,0,1,0\n" +
                "24,merchandise,,0,0,0,0,0,1,0\n" +
                "25,monetary,,0,0,0,0,0,1,0\n" +
                "26,overdue,,0,0,0,0,0,1,0\n" +
                "27,phony,,0,0,0,0,0,1,0\n" +
                "28,preliminary,,0,0,0,0,0,1,0\n" +
                "29,prosperity,,0,0,0,0,0,1,0\n" +
                "30,quits,,0,0,0,0,0,1,0\n" +
                "31,ratify,,0,0,0,0,0,1,0\n" +
                "32,reimburse,,0,0,0,0,0,1,0\n" +
                "33,restraint,,0,0,0,0,0,1,0\n" +
                "34,resumption,,0,0,0,0,0,1,0\n" +
                "35,retailer,,0,0,0,0,0,1,0\n" +
                "36,sanction,,0,0,0,0,0,1,0\n" +
                "37,significant,,0,0,0,0,0,1,0\n" +
                "38,smuggle,,0,0,0,0,0,1,0\n" +
                "39,specimen,,0,0,0,0,0,1,0\n" +
                "40,subsidiary,,0,0,0,0,0,1,0\n" ;

        String[] dataArray = data.split("\n");
        for (int i = 0; i < 40; i++) {

            String[] wordsArray = dataArray[i].split(",");
            int id = Integer.parseInt(wordsArray[0]);
            sqlite.Words wordsAdd = new sqlite.Words(id, wordsArray[1], "", 0, 0, 0, 0, 0, 1, 0);
            insertWords(wordsAdd);

        }
//        for (int i = 0; i < dataArray.length; i++) {
//
//            String[] wordsArray = dataArray[i].split(",");
//            int id = Integer.parseInt(wordsArray[0]);
//            sqlite.Words wordsAdd = new sqlite.Words(id, wordsArray[1], "", 0, 0, 0, 0, 0, 1, 0);
//            insertWords(wordsAdd);
//
//        }

    }

    public void sampleMeaning() {
        String data = "1,1,affair,[n.],事情、業務, , , , , , \n" +
                "2,2,affluent,[adj.] ,豐富的、富裕的, , , , , , \n" +
                "3,3,attain,[v.],達到, , , , , , \n" +
                "4,4,authentic,[adj.] ,可信的、貨真價實的, , , , , , \n" +
                "5,5,audit,[n.],查帳, , , , , , \n" +
                "6,6,auction,[n.],拍賣, , , , , , \n" +
                "7,6,auction,[v.],拍賣, , , , , , \n" +
                "8,7,barter,[n.],以物易物, , , , , , \n" +
                "9,8,boost,[n.],促進、推進, , , , , , \n" +
                "10,9,cargo,[n.],貨物、船貨, , , , , , \n" +
                "11,10,circumstance,[n.],情形、環境, , , , , , \n" +
                "12,11,consignment,[n.],委託, , , , , , \n" +
                "13,12,counsel,[n.],忠告、商議, , , , , , \n" +
                "14,13,diversification,[adj.] ,變化、多樣化, , , , , , \n" +
                "15,14,expiration,[n.],期滿、終止, , , , , , \n" +
                "16,15,exploitation,[n.],開發、開採, , , , , , \n" +
                "17,16,exemption,[n.],解除、免除、免稅, , , , , , \n" +
                "18,17,flourish,[v.],興隆、活躍, , , , , , \n" +
                "19,18,freight,[n.],貨物, , , , , , \n" +
                "20,18,freight,[v.],裝貨、使充滿, , , , , , \n" +
                "21,19,genuine,[adj.] ,真正的, , , , , , \n" +
                "22,20,grant,[v.],許可、授予, , , , , , \n" +
                "23,21,imposition,[n.],強迫接受, , , , , , \n" +
                "24,22,inevitable,[adj.] ,不可避免的, , , , , , \n" +
                "25,23,lease,[n.],租約、租契 , , , , , , \n" +
                "26,23,lease,[v.],出租, , , , , , \n" +
                "27,24,merchandise,[n.],商品、貨物, , , , , , \n" +
                "28,24,merchandise,[v.],買賣、經營, , , , , , \n" +
                "29,25,monetary,[adj.] ,貨幣的、金融的, , , , , , \n" +
                "30,26,overdue,[adj.] ,遲到的、到期未付的, , , , , , \n" +
                "31,27,phony,[adj.] ,假冒的、偽造的, , , , , , \n" +
                "32,28,preliminary,[n.],開端、初步行動, , , , , , \n" +
                "33,28,preliminary,[adj.],初步的、預備的, , , , , , \n" +
                "34,29,prosperity,[n.],繁榮、興旺, , , , , , \n" +
                "35,30,quits,[adj.] ,相等的、兩相抵銷的, , , , , , \n" +
                "36,31,ratify,[v.],批准、認可, , , , , , \n" +
                "37,32,reimburse,[v.],償還、付還, , , , , , \n" +
                "38,33,restraint,[n.],抑制、克制, , , , , , \n" +
                "39,34,resumption,[n.],取回、恢復, , , , , , \n" +
                "40,35,retailer,[n.],零售商人, , , , , , \n" +
                "41,36,sanction,[n.],核准、約束力, , , , , , \n" +
                "42,36,sanction,[v.],批准、許可, , , , , , \n" +
                "43,37,significant,[adj.] ,重大的、有意義的, , , , , , \n" +
                "44,38,smuggle,[v.],走私, , , , , , \n" +
                "45,39,specimen,[n.],樣品、標本, , , , , , \n" +
                "46,40,subsidiary,[n.],子公司、附屬機構, , , , , , \n" +
                "47,40,subsidiary,[adj.],輔助的、補充的, , , , , , \n" ;
        String[] dataArray = data.split("\n");
        for (int i = 0; i < dataArray.length; i++) {
            String[] wordsArray = dataArray[i].split(",");
            int sub_id = Integer.parseInt(wordsArray[0]);
            int id = Integer.parseInt(wordsArray[1]);
            Meaning meaningAdd = new Meaning(sub_id, id, wordsArray[2], wordsArray[3], wordsArray[4], "", "", "", "", "", "", "");
            insertMeaning(meaningAdd);
        }

    }

//    public void sampleExp() {
//        for (int i = 1; i <= 300; i++) {
//            Exp expAdd = new Exp("user_test", i, 0, 0, 0, "");
//            insertExp(expAdd);
//        }
//    }


}

package persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alan.resende on 31/01/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper{

    private static final String NOME_BANCO = "confirmaPonto.db";
    private static final int VERSAO = 12;

    private static String PONTOS = "CREATE TABLE pontos(" +
            "cpf INTEGER(11)," +
            "codigo TEXT," +
            "nome TEXT," +
            "endereco TEXT," +
            "latitude REAL," +
            "longitude REAL," +
            "dataHora TEXT" + ");";

    private static String PENDENCIAS = "CREATE TABLE pendencias(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "codigo TEXT," +
            "nome TEXT," +
            "endereco TEXT," +
            "latitude REAL," +
            "longitude REAL," +
            "dataHora TEXT" + ");";

    private static String CLIENTES = "CREATE TABLE clientes(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "latitude REAL," +
            "longitude REAL," +
            "nomePonto VARCHAR," +
            "codigoCliente VARCHAR," +
            "cdPontoEntidade INTEGER," +
            "endereco VARCHAR," +
            "numero VARCHAR," +
            "bairro VARCHAR," +
            "cidade VARCHAR," +
            "uf VARCHAR," +
            "timestamp INTEGER," +
            "tipoPonto INTEGER" + ");";

    private static String NOVOMARKER = "CREATE TABLE novomarker(" +
            "latitude REAL," +
            "longitude REAL," +
            "nomePonto VARCHAR," +
            "codigoCliente VARCHAR," +
            "endereco VARCHAR" + ");";

    private static String GUARDADADOS = "CREATE TABLE guardaDados(" +
            "id INTEGER,"+
            "codigo TEXT,"+
            "nome TEXT,"+
            "endereco TEXT,"+
            "latitude REAL,"+
            "longitude REAL,"+
            "dataHoraConfirmacao TEXT,"+
            "cdUsuario TEXT,"+
            "cdEntidadeSistema TEXT,"+
            "tokenSessao TEXT" + ");";

    private  static String USUARIO = "CREATE TABLE usuario(" +
            "usuario TEXT," +
            "senha TEXT," +
            "codigo INTEGER," +
            "mensagem TEXT" +");";

    public DatabaseHandler(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PONTOS);
        db.execSQL(PENDENCIAS);
        db.execSQL(CLIENTES);
        db.execSQL(NOVOMARKER);
        db.execSQL(GUARDADADOS);
        db.execSQL(USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE pontos");
        db.execSQL("DROP TABLE pendencias");
        db.execSQL("DROP TABLE clientes");
        db.execSQL("DROP TABLE novomarker");
        db.execSQL("DROP TABLE guardaDados");
        db.execSQL("DROP TABLE usuario");
        onCreate(db);
    }
}

package mobile.che.com.oddymobstar.chemobile.service.handler;

import org.json.JSONException;

import message.CheMessage;
import mobile.che.com.oddymobstar.chemobile.database.DBHelper;

/**
 * Created by timmytime on 30/01/16.
 */
public class MissileHandler extends MessageHandler {
    public MissileHandler(DBHelper dbHelper) {
        super(dbHelper);
    }

    @Override
    public void handle(CheMessage cheMessage) throws JSONException {

    }
}

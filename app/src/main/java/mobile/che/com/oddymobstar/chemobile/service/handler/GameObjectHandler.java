package mobile.che.com.oddymobstar.chemobile.service.handler;

import android.util.Log;

import org.json.JSONException;

import message.CheMessage;
import message.GameObject;
import mobile.che.com.oddymobstar.chemobile.database.DBHelper;
import mobile.che.com.oddymobstar.chemobile.util.MessageFactory;
import util.Tags;

/**
 * Created by timmytime on 30/01/16.
 */
public class GameObjectHandler extends MessageHandler {

    private final MessageFactory messageFactory;
    private CheCallbackInterface cheCallback;

    public GameObjectHandler(DBHelper dbHelper, MessageFactory messageFactory) {
        super(dbHelper);
        this.messageFactory = messageFactory;
    }

    //probably not required anyway leave here.
    public void addCheCallback(CheCallbackInterface cheCallback) {
        this.cheCallback = cheCallback;
    }

    @Override
    public void handle(CheMessage cheMessage) throws JSONException {

        GameObject gameObject = (GameObject) cheMessage.getMessage(Tags.GAME_OBJECT);

        switch (gameObject.getState()) {
            case Tags.PURCHASE:
                //simple case under test now.
                mobile.che.com.oddymobstar.chemobile.model.GameObject model = new mobile.che.com.oddymobstar.chemobile.model.GameObject();
                model.setKey(gameObject.getKey());
                model.setType(gameObject.getType());
                model.setSubType(gameObject.getSubType());
                dbHelper.addGameObject(model);

                Log.d("purchase", "have purchased " + model.getType() + " " + model.getSubType());
                break;
            case Tags.GAME_OBJECT_ADD:
                break;
            case Tags.GAME_OBJECT_HIT:
                break;
            case Tags.GAME_OBJECT_MOVE:
                break;
            case Tags.GAME_OBJECT_DESTROYED:
                break;
            case Tags.MISSILE_ADDED:
                break;
            case Tags.MISSILE_REMOVED:
                break;

        }
    }

}

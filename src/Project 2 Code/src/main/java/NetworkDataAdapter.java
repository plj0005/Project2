import com.google.gson.Gson;

public class NetworkDataAdapter implements IDataAdapter {

    String host = "localhost";
    int port = 1000;

    Gson gson = new Gson();
    SocketNetworkAdapter adapter = new SocketNetworkAdapter();
    MessageModel msg = new MessageModel();

    @Override
    public int connect(String dbfile) {
        int pos = dbfile.indexOf(":");
        host = dbfile.substring(0, pos);
        port = Integer.parseInt(dbfile.substring(pos+1, dbfile.length()));
        return 0;
    }

    @Override
    public int disconnect() {
        return 0;
    }

    @Override
    public ProductModel loadProduct(int id) {
        msg.code = MessageModel.GET_PRODUCT;
        msg.data = Integer.toString(id);
        try {
            msg = adapter.send(msg, host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (msg.code == MessageModel.OPERATION_FAILED)
            return null;
        else {
            return gson.fromJson(msg.data, ProductModel.class);
        }
    }

    @Override
    public int saveProduct(ProductModel model) {
        msg.code = MessageModel.PUT_PRODUCT;
        msg.data = gson.toJson(model);

        try {
            msg = adapter.send(msg, host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (msg.code == MessageModel.OPERATION_FAILED)
            return IDataAdapter.PRODUCT_SAVE_FAILED;
        else {
            return IDataAdapter.PRODUCT_SAVE_OK;
        }
    }

    @Override
    public CustomerModel loadCustomer(int id) {
        msg.code = MessageModel.GET_CUSTOMER;
        msg.data = Integer.toString(id);
        try {
            msg = adapter.send(msg, host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (msg.code == MessageModel.OPERATION_FAILED)
            return null;
        else {
            return gson.fromJson(msg.data, CustomerModel.class);
        }
    }

    @Override
    public int saveCustomer(CustomerModel model) {
        msg.code = MessageModel.PUT_CUSTOMER;
        msg.data = gson.toJson(model);

        try {
            msg = adapter.send(msg, host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (msg.code == MessageModel.OPERATION_FAILED)
            return IDataAdapter.CUSTOMER_SAVE_FAILED;
        else {
            return IDataAdapter.CUSTOMER_SAVE_OK;
        }
    }



    @Override
    public PurchaseModel loadPurchase(int id) {
        msg.code = MessageModel.GET_PURCHASE;
        msg.data = Integer.toString(id);
        try {
            msg = adapter.send(msg, host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (msg.code == MessageModel.OPERATION_FAILED)
            return null;
        else {
            return gson.fromJson(msg.data, PurchaseModel.class);
        }
    }

    @Override
    public int savePurchase(PurchaseModel model) {
        msg.code = MessageModel.PUT_PURCHASE;
        msg.data = gson.toJson(model);

        try {
            msg = adapter.send(msg, host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (msg.code == MessageModel.OPERATION_FAILED)
            return IDataAdapter.PURCHASE_SAVE_FAILED;
        else {
            return IDataAdapter.PURCHASE_SAVE_OK;
        }
    }
}
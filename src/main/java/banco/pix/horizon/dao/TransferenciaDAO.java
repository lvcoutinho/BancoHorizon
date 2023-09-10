package banco.pix.horizon.dao;

import banco.pix.horizon.infra.ConnectionFactory;
import banco.pix.horizon.model.Transferencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TransferenciaDAO implements ITransferenciaDAO {

    public Transferencia save(Transferencia transferencia) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO transferencia (conta_origem_id, conta_destino_id, valor, data) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, transferencia.getConta_origem_id());
            preparedStatement.setLong(2, transferencia.getConta_destino_id());
            preparedStatement.setBigDecimal(3, transferencia.getValor());
            preparedStatement.setObject(4, transferencia.getData());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    long id = generatedKeys.getLong(1);
                    transferencia.setId(id);
                    return transferencia;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
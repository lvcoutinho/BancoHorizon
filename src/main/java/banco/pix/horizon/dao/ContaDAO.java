package banco.pix.horizon.dao;

import banco.pix.horizon.infra.ConnectionFactory;
import banco.pix.horizon.model.Conta;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO implements IContaDAO {

    @Override
    public Conta createConta(Conta conta) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO Conta(pessoa_id, numero, digito, saldo, tipo_conta) VALUES (?, ?, ?, CAST(? AS money), ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, conta.getPessoa_id());
            preparedStatement.setInt(2, conta.getNumero());
            preparedStatement.setInt(3, conta.getDigito());
            preparedStatement.setBigDecimal(4,conta.getSaldo());
            preparedStatement.setString(5, conta.getTipo_conta());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Falha ao inserir a conta no banco de dados.");
            }

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                Long generatedID = resultSet.getLong(1);
                conta.setId(generatedID);
            } else {
                throw new SQLException("Falha ao obter o ID gerado para a conta.");
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao criar a conta no banco de dados", ex);
        }

        return conta;
    }

    @Override
    public Conta updateConta(Conta conta) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "UPDATE Conta SET numero = ?, digito = ?, saldo = CAST(? AS money), tipo_conta = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, conta.getNumero());
            preparedStatement.setInt(2, conta.getDigito());
            preparedStatement.setBigDecimal(3, conta.getSaldo());
            preparedStatement.setString(4, conta.getTipo_conta());
            preparedStatement.setLong(5, conta.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return conta;
    }

    @Override
    public void deleteConta(Long id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM Conta WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Conta> findAll() {
        String sql = "SELECT c.id, c.pessoa_id, c.numero, c.digito, " +
                "CAST(REPLACE(REPLACE(c.saldo::text, 'R$', ''), ',', '') AS NUMERIC) as saldo, " +
                "c.tipo_conta, p.nome " +
                "FROM Conta c " +
                "JOIN Pessoa p ON c.pessoa_id = p.id";

        List<Conta> contas = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                Long pessoa_id = rs.getLong("pessoa_id");
                String nomePessoa = rs.getString("nome");
                int numero = rs.getInt("numero");
                int digito = rs.getInt("digito");

                BigDecimal saldo = rs.getBigDecimal("saldo");

                int tipo_conta = rs.getInt("tipo_conta");
                String tipoConta = (tipo_conta == 0) ? "corrente" : "poupança";

                Conta conta = new Conta(id, pessoa_id, nomePessoa, numero, digito, saldo, tipoConta);
                contas.add(conta);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return contas;
    }
    @Override
    public Conta findContaById(Long id) {
        String sql = "SELECT c.id, c.pessoa_id, c.numero, c.digito, " +
                "CAST(REPLACE(REPLACE(c.saldo::text, 'R$', ''), ',', '') AS NUMERIC) as saldo, " +
                "c.tipo_conta, p.nome " +
                "FROM Conta c " +
                "JOIN Pessoa p ON c.pessoa_id = p.id " +
                "WHERE c.id = ?";

        Conta conta = null;
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long pessoa_id = rs.getLong("pessoa_id");
                String nomePessoa = rs.getString("nome");
                int numero = rs.getInt("numero");
                int digito = rs.getInt("digito");
                BigDecimal saldo = rs.getBigDecimal("saldo");
                String tipoConta = rs.getString("tipo_conta");

                conta = new Conta(id, pessoa_id, nomePessoa, numero, digito, saldo, tipoConta);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return conta;
    }


    @Override
    public Conta findContaByNumero(int numero) {
        String sql = "SELECT * FROM Conta WHERE numero = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, numero);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long pessoaId = resultSet.getLong("pessoa_id");
                numero = resultSet.getInt("numero");
                int digito = resultSet.getInt("digito");
                BigDecimal saldo = resultSet.getBigDecimal("saldo");
                String tipoConta = resultSet.getString("tipo_conta");

                return new Conta();
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

}
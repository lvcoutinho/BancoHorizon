package banco.pix.horizon.dao;

import banco.pix.horizon.infra.ConnectionFactory;
import banco.pix.horizon.model.Pessoa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PessoaDAO implements IPessoaDAO {
    public Pessoa save(Pessoa pessoa) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO Pessoa(nome, telefone, cpf) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setString(2, pessoa.getTelefone());
            preparedStatement.setString(3, pessoa.getCpf());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Falha ao inserir a pessoa no banco de dados.");
            }

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                Long generatedID = resultSet.getLong(1);
                pessoa.setId(generatedID);
            } else {
                throw new SQLException("Falha ao obter o ID gerado para a pessoa.");
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar a pessoa no banco de dados", ex);
        }

        return pessoa;
    }

    public Pessoa update(Pessoa pessoa) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "UPDATE Pessoa SET nome = ?, telefone = ?, cpf = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setString(2, pessoa.getTelefone());
            preparedStatement.setString(3, pessoa.getCpf());
            preparedStatement.setLong(4, pessoa.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return pessoa;
    }

    public void delete(Long id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM Pessoa WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Pessoa> findAll() {
        String sql = "SELECT id, nome, telefone, cpf FROM Pessoa";

        List<Pessoa> pessoas = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String cpf = rs.getString("cpf");

                Pessoa pessoa = new Pessoa(id, nome, telefone, cpf);
                pessoas.add(pessoa);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return pessoas;
    }

    public Optional<Pessoa> findById(Long id) {
        String sql = "SELECT id, nome, telefone, cpf FROM Pessoa WHERE id = ?";

        Pessoa pessoa = null;
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long pKey = rs.getLong("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String cpf = rs.getString("cpf");

                pessoa = new Pessoa(pKey, nome, telefone, cpf);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return Optional.ofNullable(pessoa);
    }

    @Override
    public Optional<Pessoa> findByNumeroConta(int numero) {
        String sql = "SELECT p.id, p.nome, p.telefone, p.cpf FROM Pessoa p " +
                "JOIN Conta c ON p.id = c.pessoa_id " +
                "WHERE c.numero = ?";

        Pessoa pessoa = null;
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, numero);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String cpf = rs.getString("cpf");

                pessoa = new Pessoa(id, nome, telefone, cpf);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return Optional.ofNullable(pessoa);
    }
}

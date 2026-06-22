package org.example.pacientes.dao;

import org.example.pacientes.modelo.Paciente;
import org.example.pacientes.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAOImpl implements PacienteDAO {

    @Override
    public void insertar(Paciente p) {

        String sql = "INSERT INTO pacientes VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getCedula());
            ps.setString(2, p.getNombres());
            ps.setString(3, p.getApellidos());
            ps.setDate(4, Date.valueOf(p.getFechaNac()));
            ps.setString(5, p.getGenero());
            ps.setString(6, p.getTelefono());
            ps.setString(7, p.getEmail());
            ps.setString(8, p.getTipoSangre());
            ps.setString(9, p.getAlergias());
            ps.setBoolean(10, p.isActivo());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Paciente> listar() {

        List<Paciente> lista = new ArrayList<>();

        String sql = "SELECT * FROM pacientes ORDER BY apellidos";

        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Paciente p = new Paciente();

                p.setCedula(rs.getString("cedula"));
                p.setNombres(rs.getString("nombres"));
                p.setApellidos(rs.getString("apellidos"));
                p.setFechaNac(rs.getDate("fecha_nac").toLocalDate());
                p.setGenero(rs.getString("genero"));
                p.setTelefono(rs.getString("telefono"));
                p.setEmail(rs.getString("email"));
                p.setTipoSangre(rs.getString("tipo_sangre"));
                p.setAlergias(rs.getString("alergias"));
                p.setActivo(rs.getBoolean("activo"));

                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void actualizar(Paciente p) {

        String sql = "UPDATE pacientes SET nombres=?, apellidos=?, fecha_nac=?, genero=?, telefono=?, email=?, tipo_sangre=?, alergias=?, activo=? WHERE cedula=?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombres());
            ps.setString(2, p.getApellidos());
            ps.setDate(3, Date.valueOf(p.getFechaNac()));
            ps.setString(4, p.getGenero());
            ps.setString(5, p.getTelefono());
            ps.setString(6, p.getEmail());
            ps.setString(7, p.getTipoSangre());
            ps.setString(8, p.getAlergias());
            ps.setBoolean(9, p.isActivo());
            ps.setString(10, p.getCedula());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(String cedula) {

        String sql = "DELETE FROM pacientes WHERE cedula=?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cedula);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Paciente buscarPorCedula(String cedula) {

        String sql = "SELECT * FROM pacientes WHERE cedula=?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cedula);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Paciente(
                        rs.getString("cedula"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getDate("fecha_nac").toLocalDate(),
                        rs.getString("genero"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("tipo_sangre"),
                        rs.getString("alergias"),
                        rs.getBoolean("activo")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
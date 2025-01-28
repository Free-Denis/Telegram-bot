package ru.bot.database;

import ru.bot.database.dto.ArticleDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ArticleDAOImpl implements ArticleDAO {

    @Override
    public ArticleDTO get(int id) {

        Database db = Database.getDatabase();
        Connection conn = db.getConnection();

        try {
            String sql = "SELECT * from Articles WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int article_id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String url = rs.getString("url");
                String published_date = rs.getString("published_date");
                int symbols_count = rs.getInt("symbols_count");

                return new ArticleDTO(article_id, title, author, url, published_date, symbols_count);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ArticleDTO> getAll() {
        return List.of();
    }

    @Override
    public int save(ArticleDTO articleDTO) {
        return 0;
    }

    @Override
    public int insert(ArticleDTO articleDTO) {
        return 0;
    }

    @Override
    public int update(ArticleDTO articleDTO) {
        return 0;
    }

    @Override
    public int delete(ArticleDTO articleDTO) {
        return 0;
    }
}

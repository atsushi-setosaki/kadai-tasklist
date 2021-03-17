package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tasklist;
import util.DBUtil;

/**
 * Servlet implementation class DestroyServle
 */
@WebServlet("/destroy")
public class DestroyServle extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestroyServle() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            // セッションスコープからタスクのIDを取得して
            // 該当のIDのタスク1件のみをデータベースから取得
            Tasklist t=em.find(Tasklist.class,(Integer)(request.getSession().getAttribute("tasks_id")));


            em.getTransaction().begin();
            em.remove(t);//データ削除
            em.getTransaction().commit();
            em.close();

            request.getSession().removeAttribute("tasks_id");

            response.sendRedirect(request.getContextPath() + "/index");

    }
    }

}

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Conversor</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }
        body { background: linear-gradient(135deg, #1e293b 0%, #0f172a 100%); min-height: 100vh; display: flex; align-items: center; justify-content: center; padding: 20px; }
        .login-card { background: #ffffff; width: 100%; max-width: 400px; padding: 40px 30px; border-radius: 16px; box-shadow: 0 20px 25px -5px rgba(0, 0, 0, 0.3), 0 10px 10px -5px rgba(0, 0, 0, 0.2); text-align: center; }
        .avatar-container { width: 120px; height: 120px; margin: 0 auto 20px; border-radius: 50%; overflow: hidden; border: 4px solid #3b82f6; box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1); }
        .avatar-container img { width: 100%; height: 100%; object-fit: cover; }
        .title { font-size: 1.5rem; color: #0f172a; font-weight: 700; margin-bottom: 6px; }
        .subtitle { font-size: 0.875rem; color: #64748b; margin-bottom: 24px; }
        .form-group { text-align: left; margin-bottom: 18px; }
        .form-group label { display: block; font-size: 0.875rem; font-weight: 600; color: #334155; margin-bottom: 6px; }
        .form-group input { width: 100%; padding: 12px 14px; font-size: 0.95rem; border: 1.5px solid #cbd5e1; border-radius: 8px; outline: none; transition: all 0.2s; }
        .form-group input:focus { border-color: #3b82f6; box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.2); }
        .btn-submit { width: 100%; background-color: #2563eb; color: #ffffff; padding: 12px; font-size: 1rem; font-weight: 600; border: none; border-radius: 8px; cursor: pointer; transition: background 0.2s, transform 0.1s; margin-top: 10px; }
        .btn-submit:hover { background-color: #1d4ed8; }
        .btn-submit:active { transform: scale(0.98); }
        .alert-error { background-color: #fef2f2; border-left: 4px solid #ef4444; color: #991b1b; padding: 10px 12px; font-size: 0.875rem; border-radius: 4px; margin-top: 18px; text-align: left; }
    </style>
</head>
<body>
    <div class="login-card">
        <div class="avatar-container">
            <img src="images/monster.jpeg" alt="Avatar Monster">
        </div>
        <h1 class="title">Sistema de Conversión</h1>
        <p class="subtitle">Ingrese sus credenciales de acceso</p>

        <form action="LoginServlet" method="POST">
            <div class="form-group">
                <label for="usuario">Usuario</label>
                <input type="text" id="usuario" name="usuario" placeholder="Ej. admin" required autocomplete="off">
            </div>

            <div class="form-group">
                <label for="clave">Contraseña</label>
                <input type="password" id="clave" name="clave" placeholder="••••••••" required>
            </div>

            <button type="submit" class="btn-submit">Iniciar Sesión</button>
        </form>

        <% 
            String mensajeError = (String) request.getAttribute("error");
            if (mensajeError != null) {
        %>
            <div class="alert-error">
                <%= mensajeError %>
            </div>
        <% } %>
    </div>
</body>
</html>
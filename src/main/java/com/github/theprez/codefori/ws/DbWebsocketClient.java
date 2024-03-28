package com.github.theprez.codefori.ws;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.StatusCode;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

import com.github.theprez.codefori.DataStreamProcessor;
import com.github.theprez.codefori.SystemConnection;

public class DbWebsocketClient extends WebSocketAdapter {
  private final CountDownLatch closureLatch = new CountDownLatch(1);
  private final DataStreamProcessor io;

  DbWebsocketClient(String host, String user, String pass) throws UnsupportedEncodingException {
    super();
    SystemConnection conn = new SystemConnection(host, user, pass);
    io = getDataStream(this, conn);
  }

  @Override
  public void onWebSocketConnect(Session sess) {
    super.onWebSocketConnect(sess);
    System.out.println("Socket Connected: " + sess);
  }

  @Override
  public void onWebSocketText(String message) {
    super.onWebSocketText(message);
    io.run(message);
  }

  @Override
  public void onWebSocketClose(int statusCode, String reason) {
    super.onWebSocketClose(statusCode, reason);
    closureLatch.countDown();
  }

  @Override
  public void onWebSocketError(Throwable cause) {
    super.onWebSocketError(cause);
    cause.printStackTrace(System.err);
  }

  public void awaitClosure() throws InterruptedException {
    closureLatch.await();
  }

  private static DataStreamProcessor getDataStream(DbWebsocketClient endpoint, SystemConnection conn) throws UnsupportedEncodingException {
    InputStream in = new ByteArrayInputStream(new byte[0]);

    OutputStream outStream = new OutputStream() {
      private StringBuilder string = new StringBuilder();

      @Override
      public void write(int b) {
        this.string.append((char) b);
      }

      public String toString() {
        return this.string.toString();
      }

      @Override
      public void flush() throws IOException {
        endpoint.getRemote().sendString(this.toString());
        this.string.setLength(0);
      }
    };

    PrintStream out = new PrintStream(outStream);

    return new DataStreamProcessor(in, out, conn, false);
  }
}
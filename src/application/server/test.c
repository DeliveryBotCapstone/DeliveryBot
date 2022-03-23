#include <sys/types.h>
#include <sys/socket.h>
#include <unistd.h>
#include <stdlib.h>
#include <arpa/inet.h>
#include <string.h>
#include <sys/un.h>
#include <stdio.h>

#define PORTNUM 1111

int main(int argc, char *argv[]) {
  char buf[256];
  char *msg = "message";
  struct sockaddr_in sin, cli;
  int sd, ns, clientlen = sizeof(cli);

  if((sd = socket(AF_INET, SOCK_STREAM, 0)) == -1 ) { // 소켓 생성
        perror("socket");
        exit(1);
  }
  memset((char *)&sin, '\0', sizeof(sin));
  sin.sin_family = AF_INET;

  sin.sin_port = htons(PORTNUM);
  sin.sin_addr.s_addr = htonl(INADDR_ANY);

  if(bind(sd, (struct sockaddr *)&sin, sizeof(sin))) {
        perror("bind");
        exit(1);
  }
  if(listen(sd, 5)) {
        perror("listen");
        exit(1);
  }
  printf("Connection Wating...\n");
  if((ns = accept(sd, (struct sockaddr *)&cli, &clientlen)) == -1) {
        perror("accept");
        exit(1);
  }

  if(recv(ns, buf, sizeof(buf) + 1, 0) == -1) { // 데이터 받기
        perror("recv");
        exit(1);
  }

  printf("From Client Message: %s\n", buf); // 출력

  sleep(10);

  if(send(ns, msg, sizeof(msg) + 1, 0) == -1) {
        perror("send");
        exit(1);
  }
  close(ns);
  close(sd);

  return 0;
}

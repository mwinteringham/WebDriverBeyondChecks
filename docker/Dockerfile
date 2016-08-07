FROM centos:7
MAINTAINER David Lawrence <dkl@mozilla.com>

# Environment configuration
ENV BUGS_DB_DRIVER mysql
ENV BUGS_DB_NAME bugs
ENV BUGS_DB_PASS bugs
ENV BUGS_DB_HOST localhost

ENV BUGZILLA_USER bugzilla
ENV BUGZILLA_HOME /home/$BUGZILLA_USER
ENV BUGZILLA_ROOT $BUGZILLA_HOME/devel/htdocs/bugzilla
ENV BUGZILLA_URL http://localhost/bugzilla

ENV GITHUB_BASE_GIT https://github.com/bugzilla/bugzilla
ENV GITHUB_BASE_BRANCH 5.0
ENV GITHUB_QA_GIT https://github.com/bugzilla/qa

ENV ADMIN_EMAIL admin@bugzilla.org
ENV ADMIN_PASS password

# Distribution package installation
COPY rpm_list /rpm_list
RUN yum -y -q update \
    && yum -y -q install https://dev.mysql.com/get/mysql-community-release-el7-5.noarch.rpm epel-release \
    && yum -y -q install `cat /rpm_list` \
    && yum clean all

# User configuration
RUN useradd -m -G wheel -u 1000 -s /bin/bash $BUGZILLA_USER \
    && passwd -u -f $BUGZILLA_USER \
    && echo "bugzilla:bugzilla" | chpasswd

# Apache configuration
COPY bugzilla.conf /etc/httpd/conf.d/bugzilla.conf

# MySQL configuration
COPY my.cnf /etc/my.cnf
RUN chmod 644 /etc/my.cnf \
    && chown root.root /etc/my.cnf \
    && rm -rf /etc/mysql \
    && rm -rf /var/lib/mysql/* \
    && /usr/bin/mysql_install_db --user=$BUGZILLA_USER --basedir=/usr --datadir=/var/lib/mysql

# Sudoer configuration
COPY sudoers /etc/sudoers
RUN chown root.root /etc/sudoers && chmod 440 /etc/sudoers

# Clone the code repo
RUN su $BUGZILLA_USER -c "git clone $GITHUB_BASE_GIT -b $GITHUB_BASE_BRANCH $BUGZILLA_ROOT"

# Copy setup and test scripts
COPY *.sh buildbot_step checksetup_answers.txt /
RUN chmod 755 /*.sh /buildbot_step

# Bugzilla dependencies and setup
RUN /install_deps.sh
RUN /bugzilla_config.sh

# Final permissions fix
RUN chown -R $BUGZILLA_USER.$BUGZILLA_USER $BUGZILLA_HOME

# Networking
RUN echo "NETWORKING=yes" > /etc/sysconfig/network
EXPOSE 80
EXPOSE 22
EXPOSE 5900
EXPOSE 3306
EXPOSE 3001

# DB-API setup
RUN cd ~ && wget http://nodejs.org/dist/v0.10.30/node-v0.10.30-linux-x64.tar.gz && sudo tar --strip-components 1 -xzvf node-v* -C /usr/local
COPY db-api/ /usr/local/db-api/
RUN cd /usr/local/db-api && npm install

# Supervisor
COPY supervisord.conf /etc/supervisord.conf
RUN chmod 700 /etc/supervisord.conf
CMD ["/usr/bin/supervisord", "-c", "/etc/supervisord.conf"]

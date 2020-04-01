/*
 * ngx_http_execute_module.c
 *
 * Author: Gandalf
 * email： zhibu1991@gmail.com
 */
#include <ngx_config.h>
#include <ngx_core.h>
#include <ngx_http.h>
#include <ngx_md5.h>
#include <mysql.h>


char tb_sale_team[2048];
int generate(char *name, char *user_id, char *mobile);
int switch_learning(void);


/* Module config */
typedef struct {
	ngx_str_t ed;
} ngx_http_execute_loc_conf_t;

typedef struct {
	char        *command;
} ngx_http_command_conf_t;

static char *ngx_http_execute(ngx_conf_t *cf, ngx_command_t *cmd, void *conf);
static void *ngx_http_execute_create_loc_conf(ngx_conf_t *cf);
static char *ngx_http_execute_merge_loc_conf(ngx_conf_t *cf, void *parent,
		void *child);

static ngx_command_t ngx_http_execute_commands[] = { { ngx_string("command"),
	NGX_HTTP_LOC_CONF | NGX_CONF_TAKE1, ngx_http_execute,
	NGX_HTTP_LOC_CONF_OFFSET, 0, NULL },

		     ngx_null_command };
/* Http context of the module */
static ngx_http_module_t ngx_http_execute_module_ctx = { NULL, /* preconfiguration */
	NULL, /* postconfiguration */

	NULL, /* create main configuration */
	NULL, /* init main configuration */

	NULL, /* create server configuration */
	NULL, /* merge server configuration */

	ngx_http_execute_create_loc_conf, /* create location configration */
	ngx_http_execute_merge_loc_conf /* merge location configration */
};

/* Module */
ngx_module_t ngx_http_execute_module = {
	NGX_MODULE_V1, &ngx_http_execute_module_ctx, /* module context */
	ngx_http_execute_commands, /* module directives */
	NGX_HTTP_MODULE, /* module type */
	NULL, /* init master */
	NULL, /* init module */
	NULL, /* init process */
	NULL, /* init thread */
	NULL, /* exit thread */
	NULL, /* exit process */
	NULL, /* exit master */
	NGX_MODULE_V1_PADDING };

void urldecode(char *dest, const char *src)
{
	const char *p = src;
	char code[3] = {0};
	unsigned long ascii = 0;
	char *end = NULL;

	while(*p)
	{
		if(*p == '%')
		{
			memcpy(code, ++p, 2);
			ascii = strtoul(code, &end, 16);
			*dest++ = (char)ascii;
			p += 2;
		}
		else
			*dest++ = *p++;
	}
}

int generate(char *name, char *user_id, char *mobile) {
	MYSQL *conn_ptr;
	MYSQL_RES *res_ptr;
	MYSQL_ROW sqlrow;
	int res;

	char useruuid[512], update_user_shop[2048];
	sprintf(useruuid,
			"select user_uuid,weixin_unionid from tb_user where user_id=%s",
			user_id);

	sprintf(update_user_shop,
			"update tb_user set shop_desc='百分百正品全球时刻',owner_name='%s',mark='QQSK', \
			user_member_role='NORMAL',mobile='%s', gmt_license=now(),member_expired_time=DATE_ADD(now(),INTERVAL 3 YEAR), \
			shop_number=UNIX_TIMESTAMP(),	shop_name='%s的店铺',remark='手动开通',default_member_id='%s', parent_member_id='9430425', \
			flagship_id='20', manager_id='20', team_id='7' where user_id='%s'",
			name, mobile, name, user_id, user_id);
	conn_ptr = mysql_init(NULL);

	if (mysql_real_connect(conn_ptr,
			"rm-bp136nul4zokv1i51mo.mysql.rds.aliyuncs.com", "super",
			"TianSou820820", "qqsk", 0, NULL, 0)) {
		char value = 1;
		mysql_options(conn_ptr, MYSQL_OPT_RECONNECT, &value);
		res = mysql_query(conn_ptr, useruuid);
		if (res) {
			printf("SELECT error:%s\n", mysql_error(conn_ptr));

		} else {
			res_ptr = mysql_store_result(conn_ptr);
			if (res_ptr) {
				mysql_num_fields(res_ptr);

				while ((sqlrow = mysql_fetch_row(res_ptr)) != NULL) {

					if (sqlrow[1] != NULL && sqlrow[0] != NULL) {
						mysql_query(conn_ptr, update_user_shop);
						sprintf(tb_sale_team,
								"INSERT  INTO tb_sale_team (user_uuid, user_puuid, user_level_id, user_level_name, user_channel, user_union_id, user_id, user_pid, CREATED_TIME, CREATED_BY) "
										" VALUES ('%s','257325690293190656','102','NORMAL','QQSK','%s','%s','9430425',now(),'zhangbo')",
								sqlrow[0], sqlrow[1], user_id);
					} else {
						printf("user_uuid or weixin_unionid is empty\n");

					}

				}

				mysql_free_result(res_ptr);
				mysql_close(conn_ptr);
			}
		}
	}
	return 0;
}

int open_live(char *user_id) {
	MYSQL *conn_ptr;
	char open_user_live[2048];
	sprintf(open_user_live,
			"INSERT INTO tb_live_popshop ( user_id, is_open_live,is_open_popshop ) VALUES ('%s', 1,1)",
			 user_id);
	conn_ptr = mysql_init(NULL);

	if (mysql_real_connect(conn_ptr,
			"rm-bp136nul4zokv1i51mo.mysql.rds.aliyuncs.com", "super",
			"TianSou820820", "qqsk", 0, NULL, 0)) {
		char value = 1;
		mysql_options(conn_ptr, MYSQL_OPT_RECONNECT, &value);
	    mysql_query(conn_ptr, open_user_live);
	    mysql_close(conn_ptr);
	}
	return 0;
}

int switch_learning(void) {

	MYSQL *conn_ptr;

	conn_ptr = mysql_init(NULL);
	if (!conn_ptr) {
		return 1;
	}
	if( mysql_real_connect(conn_ptr, "47.110.157.66", "TianSou", "TianSou820",
			"qqsk_manager", 0, NULL, 0)){
	char value2 = 1;
		mysql_options(conn_ptr, MYSQL_OPT_RECONNECT, &value2);
			mysql_query(conn_ptr, tb_sale_team);
			mysql_close(conn_ptr);
	}
	return 0;
}

/* Handler function */
static ngx_int_t ngx_http_execute_handler(ngx_http_request_t *r) {
	ngx_int_t rc;
	ngx_buf_t *b;
	ngx_chain_t out;
	char *a0 = NULL, *a1 = NULL, *a2 = NULL, *a3 = NULL;
	char outargs[128] = { 0 };
	char outargs1[128] = { 0 };
	char outargs2[128] = { 0 };

	char args_buf[1024], *p;
	char *delim = "&";
	const char *fmt_base = "%.*s";

	if (!(r->method & (NGX_HTTP_GET | NGX_HTTP_POST))) {
		return NGX_HTTP_NOT_ALLOWED;
	}
	if (!ngx_strncmp(r->uri.data, "/license/api", 12)) {

		snprintf(args_buf, sizeof(args_buf), fmt_base, r->args.len,
				r->args.data);
		ngx_log_debug3(NGX_LOG_DEBUG_HTTP, r->connection->log, 0,
				"http header4: \"%V?%V\"[%s]", &r->uri, &r->args, args_buf);
		a0 = strtok(args_buf, delim);
		if (a0 != NULL) {
			a1 = strstr(a0, "=") + 1;
			p = strtok(NULL, delim);
			a2 = strstr(p, "=") + 1;
			p = strtok(NULL, delim);
			a3 = strstr(p, "=") + 1;

			if (strlen(a1) > 0 && strlen(a2) > 0 && strlen(a3) > 0) {
				ngx_log_debug2(NGX_LOG_DEBUG_HTTP, r->connection->log, 0,
						"http header4 4: \"%V?%V\"", &r->uri, &r->args);
				urldecode(outargs, a1);
				urldecode(outargs1, a2);
				urldecode(outargs2, a3);
				generate(outargs, outargs1, outargs2);
				switch_learning();
			} else {
				return -1;
			}
		}
		r->headers_out.content_type.len = sizeof("text/html") - 1;
		r->headers_out.content_type.data = (u_char *) "text/html";
		r->headers_out.status = NGX_HTTP_OK;
		r->headers_out.content_length_n = strlen("200");

		if (r->method == NGX_HTTP_HEAD) {
			rc = ngx_http_send_header(r);
			if (rc != NGX_OK) {
				return rc;
			}
		}

		b = ngx_pcalloc(r->pool, sizeof(ngx_buf_t));
		if (b == NULL) {
			ngx_log_error(NGX_LOG_ERR, r->connection->log, 0,
					"Failed to allocate response buffer.");
			return NGX_HTTP_INTERNAL_SERVER_ERROR;
		}
		out.buf = b;
		out.next = NULL;

		b->pos = (u_char *) "200";
		b->last = (u_char *) "200" + strlen("200");
		b->memory = 1;
		b->last_buf = 1;
		rc = ngx_http_send_header(r);
		if (rc != NGX_OK) {
			return rc;
		}
	}
	if (!ngx_strncmp(r->uri.data, "/license/live", 13)) {

		snprintf(args_buf, sizeof(args_buf), fmt_base, r->args.len,
				r->args.data);
		ngx_log_debug3(NGX_LOG_DEBUG_HTTP, r->connection->log, 0,
				"http header4: \"%V?%V\"[%s]", &r->uri, &r->args, args_buf);
		a0 = strtok(args_buf, delim);
		if (a0 != NULL) {
			a1 = strstr(a0, "=") + 1;
//			p = strtok(NULL, delim);
//			a2 = strstr(p, "=") + 1;
//			a3 = strstr(p, "=") + 1;

			if (strlen(a1) > 0) {
				ngx_log_debug2(NGX_LOG_DEBUG_HTTP, r->connection->log, 0,
						"http header4 4: \"%V?%V\"", &r->uri, &r->args);
				urldecode(outargs, a1);
				open_live(outargs);
			} else {
				return -1;
			}
		}
		r->headers_out.content_type.len = sizeof("text/html") - 1;
		r->headers_out.content_type.data = (u_char *) "text/html";
		r->headers_out.status = NGX_HTTP_OK;
		r->headers_out.content_length_n = strlen("200");

		if (r->method == NGX_HTTP_HEAD) {
			rc = ngx_http_send_header(r);
			if (rc != NGX_OK) {
				return rc;
			}
		}

		b = ngx_pcalloc(r->pool, sizeof(ngx_buf_t));
		if (b == NULL) {
			ngx_log_error(NGX_LOG_ERR, r->connection->log, 0,
					"Failed to allocate response buffer.");
			return NGX_HTTP_INTERNAL_SERVER_ERROR;
		}
		out.buf = b;
		out.next = NULL;

		b->pos = (u_char *) "200";
		b->last = (u_char *) "200" + strlen("200");
		b->memory = 1;
		b->last_buf = 1;
		rc = ngx_http_send_header(r);
		if (rc != NGX_OK) {
			return rc;
		}
	}
	return ngx_http_output_filter(r, &out);
}

static char *
ngx_http_execute(ngx_conf_t *cf, ngx_command_t *cmd, void *conf) {
	ngx_http_core_loc_conf_t *clcf;
	ngx_str_t *value;
	ngx_http_command_conf_t *com = conf;

	if (com->command) {
		return "is duplicate";
	}
	value = cf->args->elts;
	if (ngx_strcasecmp(value[1].data, (u_char *) "on") != 0) {
		return NGX_CONF_OK;
	}

	clcf = ngx_http_conf_get_module_loc_conf(cf, ngx_http_core_module);
	clcf->handler = ngx_http_execute_handler;
	ngx_conf_set_str_slot(cf, cmd, conf);

	return NGX_CONF_OK;
}

static void *
ngx_http_execute_create_loc_conf(ngx_conf_t *cf) {
	ngx_http_execute_loc_conf_t *conf;

	conf = ngx_pcalloc(cf->pool, sizeof(ngx_http_execute_loc_conf_t));
	if (conf == NULL) {
		return NGX_CONF_ERROR ;
	}
	conf->ed.len = 0;
	conf->ed.data = NULL;

	return conf;
}

static char *
ngx_http_execute_merge_loc_conf(ngx_conf_t *cf, void *parent, void *child) {
	ngx_http_execute_loc_conf_t *prev = parent;
	ngx_http_execute_loc_conf_t *conf = child;

	ngx_conf_merge_str_value(conf->ed, prev->ed, "");

	return NGX_CONF_OK;
}

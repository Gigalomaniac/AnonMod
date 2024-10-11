// This shader is created by Simmons.
// All rights reserved.

const float c_pi = 3.14159265358979323846;
const float c_delta = 0.0009765625;

const float c_amb_grd_scl = 60.0;
const float c_amb_drp_low = 0.0;
const float c_amb_drp_upp = 0.3;
const float c_amb_drp_scl = 0.72;
const float c_amb_period = 10.0;
const float c_amb_emg_t = 0.025;

const float c_flu_aspect = 6.0;
const float c_flu_grd_scl = 3.0;
const float c_flu_gravity = 0.78;
const float c_flu_trl_tub = 20.0;
const float c_flu_drp_scl = 0.71;
const float c_flu_rand_ints = 0.9;
const float c_flu_emg_t = 0.85;
const float c_flu_main_low = 0.0;
const float c_flu_main_upp = 0.4;
const float c_flu_trlw_low = 0.15;
const float c_flu_trlw_upp = 0.26;
const float c_flu_front = 0.02;
const float c_flu_ditr_low = 0.0;
const float c_flu_ditr_upp = 0.3;
const float c_flu_trl_dens = 10.0;
const vec2 c_flu_scl_n = vec2(c_flu_aspect, 1.0);

const float c_fr_bc_rat = 17.0 / 7.0;
const float c_dtrt_low = 0.3;
const float c_dtrt_upp = 1.0;

const float c_amt_range = 0.22;
const float c_dur_mult = 0.015625;
const float c_amb_wlow = 0.0;
const float c_amb_wupp = 1.0;
const float c_amb_wfac = 2.0;
const float c_fb_wlow = 0.25;
const float c_fb_wupp = 0.75;
const float c_fe_wlow = 0.0;
const float c_fe_wupp = 0.5;
const float c_slow_time_f = 0.75;

uniform sampler2D u_texture;
uniform float u_time;
uniform float u_ratio;

varying vec4 v_color;
varying vec2 v_texCoords;

float randf_d1_0(float val) {
    return fract(2053.0 * sin(fma(3079.0, val, 4099.0)));
}

float randf_d1_1(float val) {
    return fract(2579.0 * sin(fma(3593.0, val, 4621.0)));
}

float randf_d1_2(float val) {
    return fract(2819.0 * sin(fma(3847.0, val, 4871.0)));
}

float randf_d1_3(float val) {
    return fract(5147.0 * sin(fma(6151.0, val, 7177.0)));
}

float randf_d2_d1_0(vec2 val) {
    return randf_d1_2(randf_d1_0(val.x) + randf_d1_1(val.y));
}

vec2 randf_d2_0(vec2 val) {
    return vec2(randf_d1_2(randf_d1_0(val.x) + randf_d1_1(val.y)), randf_d1_2(randf_d1_1(val.x) - randf_d1_0(val.y)));
}

float remap_sect(float v, float f, float g, float m, float n) {
    return fma((v - f) / (g - f), n - m, m);
}

float smst_emerge(float peak, float val) {
    return smoothstep(0.0, peak, val) * (1.0 - smoothstep(peak, 1.0, val));
}

float ambient_drops(vec2 coord, float time) {
    vec2 t_ful_crd = c_amb_grd_scl * coord;
    float t_drp_ints = randf_d2_d1_0(floor(t_ful_crd));

    return (1.0 - smoothstep(c_amb_drp_low, c_amb_drp_upp, length(c_amb_drp_scl * randf_d2_0(floor(t_ful_crd)) - fract(t_ful_crd) + 0.5 * (1.0 - c_amb_drp_scl)))) * fract(c_amb_period * t_drp_ints) * smst_emerge(c_amb_emg_t, fract(t_drp_ints + time));
}

vec2 fluent_drops(vec2 coord, float time) {
    vec2 t_loc_crd = coord;
    vec2 t_loc_id = vec2(0.0);
    vec2 t_grd_inf = vec2(0.0);
    vec2 t_grd_loc = vec2(0.0);
    vec2 t_drp_inf = vec2(0.0);
    float t_drp_rad = 0.0;
    float t_trl_frt = 0.0;

    t_loc_crd.y += c_flu_gravity * time;
    t_loc_id = floor(c_flu_grd_scl * c_flu_scl_n * t_loc_crd);
    t_loc_crd.y += randf_d1_3(t_loc_id.x);
    t_loc_id = floor(c_flu_grd_scl * c_flu_scl_n * t_loc_crd);

    t_grd_inf = randf_d2_0(t_loc_id);
    t_grd_loc = fract(c_flu_grd_scl * c_flu_scl_n * t_loc_crd) - vec2(0.5, 0.0);

    t_drp_inf = vec2(t_grd_inf.x - 0.5, c_flu_trl_tub * coord.y);
    t_drp_inf.x += sin(t_drp_inf.y + sin(t_drp_inf.y)) * (0.5 - abs(t_drp_inf.x)) * (t_grd_inf.y - 0.5);
    t_drp_inf.x *= c_flu_drp_scl;
    t_drp_inf.y = c_flu_rand_ints * smst_emerge(c_flu_emg_t, fract(time + t_grd_inf.y)) + 0.5 * (1.0 - c_flu_rand_ints);

    t_drp_rad = sqrt(1.0 - smoothstep(t_drp_inf.y, 1.0, t_grd_loc.y));
    t_trl_frt = smoothstep(-c_flu_front, c_flu_front, t_grd_loc.y - t_drp_inf.y);

    return vec2(1.0 - smoothstep(c_flu_main_low, c_flu_main_upp, length(c_flu_scl_n.yx * (t_drp_inf - t_grd_loc))) + (1.0 - smoothstep(c_flu_ditr_low, c_flu_ditr_upp, length(vec2(t_drp_inf.x, fract(c_flu_trl_dens * coord.y) + t_grd_loc.y - 0.5) - t_grd_loc))) * t_drp_rad * t_trl_frt, (1.0 - smoothstep(c_flu_trlw_low * t_drp_rad * t_drp_rad, c_flu_trlw_upp * t_drp_rad, abs(t_drp_inf.x - t_grd_loc.x))) * t_trl_frt * t_drp_rad * t_drp_rad);
}

vec2 prepare_frag(vec2 coord, float time, float amb, float fb, float fe) {
    vec2 t_fb = fb * fluent_drops(coord, time);
    vec2 t_fe = fe * fluent_drops(c_fr_bc_rat * coord, time);
    float t_amb = amb * ambient_drops(coord, time);

    return vec2(smoothstep(c_dtrt_low, c_dtrt_upp, t_fb.x + t_fe.x + t_amb), max(t_fb.y, t_fe.y));
}

void main() {
    vec2 t_coord = vec2(v_texCoords.x, remap_sect(v_texCoords.y, 0.0, 1.0, 0.0, u_ratio));

    float t_rain_wgt = c_amt_range * sin(c_dur_mult * u_time) + (1.0 - c_amt_range);
    float t_amb = c_amb_wfac * smoothstep(c_amb_wlow, c_amb_wupp, t_rain_wgt);
    float t_fb = smoothstep(c_fb_wlow, c_fb_wupp, t_rain_wgt);
    float t_fe = smoothstep(c_fe_wlow, c_fe_wupp, t_rain_wgt);

    vec2 t_cur = prepare_frag(t_coord, c_slow_time_f * u_time, t_amb, t_fb, t_fe);
    float t_dx = prepare_frag(vec2(t_coord.x + c_delta, t_coord.y), c_slow_time_f * u_time, t_amb, t_fb, t_fe).x;
    float t_dy = prepare_frag(vec2(t_coord.x, t_coord.y + c_delta), c_slow_time_f * u_time, t_amb, t_fb, t_fe).x;

    gl_FragColor = texture(u_texture, v_texCoords + vec2(t_dx - t_cur.x, t_dy - t_cur.x));
}
#version 330 core

uniform vec2 iResolution;
uniform float iTime;

in vec2 UV; // 从顶点着色器传递的 UV 坐标

out vec4 fragColor;

void main() {
    const int zoom = 40;
    const float brightness = 0.975;
    float fScale = 1.25;

    float cosRange(float amt, float range, float minimum) {
        return (((1.0 + cos(radians(amt))) * 0.5) * range) + minimum;
    }

    float time = iTime * 1.25;
    vec2 uv = UV; // 使用从顶点着色器传递的 UV
    vec2 p = (2.0 * uv - 1.0) * max(iResolution.y / iResolution.x, 1.0);

    float ct = cosRange(time * 5.0, 3.0, 1.1);
    float xBoost = cosRange(time * 0.2, 5.0, 5.0);
    float yBoost = cosRange(time * 0.1, 10.0, 5.0);

    fScale = cosRange(time * 15.5, 1.25, 0.5);

    for (int i = 1; i < zoom; i++) {
        float _i = float(i);
        vec2 newp = p;
        newp.x += 0.25 / _i * sin(_i * p.y + time * cos(ct) * 0.5 / 20.0 + 0.005 * _i) * fScale + xBoost;
        newp.y += 0.25 / _i * sin(_i * p.x + time * ct * 0.3 / 40.0 + 0.03 * float(i + 15)) * fScale + yBoost;
        p = newp;
    }

    vec3 col = vec3(0.5 * sin(3.0 * p.x) + 0.5, 0.5 * sin(3.0 * p.y) + 0.5, sin(p.x + p.y));
    col *= brightness;

    // Add border
    float vigAmt = 5.0;
    float vignette = (1.0 - vigAmt * (uv.y - 0.5) * (uv.y - 0.5)) * (1.0 - vigAmt * (uv.x - 0.5) * (uv.x - 0.5));
    float extrusion = (col.x + col.y + col.z) / 4.0;
    extrusion *= 1.5;
    extrusion *= vignette;

    fragColor = vec4(col, extrusion);
}
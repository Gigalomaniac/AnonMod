
vec3 mod289(vec3 x) {
  return x - floor(x * (1.0 / 289.0)) * 289.0;
}

vec2 mod289(vec2 x) {
  return x - floor(x * (1.0 / 289.0)) * 289.0;
}

vec3 permute(vec3 x) {
  return mod289(((x*34.0)+1.0)*x);
}

float snoise(vec2 v)
  {
  const vec4 C = vec4(0.211324865405187,  // (3.0-sqrt(3.0))/6.0
                      0.366025403784439,  // 0.5*(sqrt(3.0)-1.0)
                     -0.577350269189626,  // -1.0 + 2.0 * C.x
                      0.024390243902439); // 1.0 / 41.0
// First corner
  vec2 i  = floor(v + dot(v, C.yy) );
  vec2 x0 = v -   i + dot(i, C.xx);

// Other corners
  vec2 i1;
  //i1.x = step( x0.y, x0.x ); // x0.x > x0.y ? 1.0 : 0.0
  //i1.y = 1.0 - i1.x;
  i1 = (x0.x > x0.y) ? vec2(1.0, 0.0) : vec2(0.0, 1.0);
  // x0 = x0 - 0.0 + 0.0 * C.xx ;
  // x1 = x0 - i1 + 1.0 * C.xx ;
  // x2 = x0 - 1.0 + 2.0 * C.xx ;
  vec4 x12 = x0.xyxy + C.xxzz;
  x12.xy -= i1;

// Permutations
  i = mod289(i); // Avoid truncation effects in permutation
  vec3 p = permute( permute( i.y + vec3(0.0, i1.y, 1.0 ))
		+ i.x + vec3(0.0, i1.x, 1.0 ));

  vec3 m = max(0.5 - vec3(dot(x0,x0), dot(x12.xy,x12.xy), dot(x12.zw,x12.zw)), 0.0);
  m = m*m ;
  m = m*m ;

// Gradients: 41 points uniformly over a line, mapped onto a diamond.
// The ring size 17*17 = 289 is close to a multiple of 41 (41*7 = 287)

  vec3 x = 2.0 * fract(p * C.www) - 1.0;
  vec3 h = abs(x) - 0.5;
  vec3 ox = floor(x + 0.5);
  vec3 a0 = x - ox;

// Normalise gradients implicitly by scaling m
// Approximation of: m *= inversesqrt( a0*a0 + h*h );
  m *= 1.79284291400159 - 0.85373472095314 * ( a0*a0 + h*h );

// Compute final noise value at P
  vec3 g;
  g.x  = a0.x  * x0.x  + h.x  * x0.y;
  g.yz = a0.yz * x12.xz + h.yz * x12.yw;
  return 130.0 * dot(m, g);
}

float rand(vec2 co)
{
   return fract(sin(dot(co.xy,vec2(12.9898,78.233))) * 43758.5453);
}

varying vec2 pos;

uniform sampler2D u_texture;
uniform vec2 u_screenSize;
uniform float u_time;
uniform float u_strength;
uniform float u_chrAb;
uniform float u_UVScl;


void main()
{
	vec2 uv = pos.xy ;
    float time = u_time*0.5;
    
    // Create large, incidental noise waves
    float noise = snoise(vec2(time, uv.y * u_UVScl * 0.2) - 0.1) * (0.1);
    
    // Offset by smaller, constant noise waves
    noise = noise + (snoise(vec2(time*1.0, uv.y * u_UVScl * 100.0)) - 0.05) * 0.05;

    noise *= u_strength;
    
    // Apply the noise as x displacement for every line
    float xpos = uv.x - noise * noise * 0.25;
	vec4 acc = texture2D(u_texture, vec2(xpos, uv.y));
    
    // Mix in some random interference for lines
     acc.rgb = mix(acc.rgb, vec3(rand(vec2(uv.y * u_UVScl * time))), noise * 0.1).rgb;
    
    // Apply a line pattern every 4 pixels
    if (floor(mod(pos.y * 0.25, 2.0)) == 0.0)
    {
        acc.rgb *= 1.0 - (2.0 * noise);
    }
    
    // Shift green/blue channels (using the red channel)
    acc.g = mix(acc.r, texture2D(u_texture, vec2(xpos + noise * u_chrAb , uv.y)).g, 1.0);
    acc.b = mix(acc.r, texture2D(u_texture, vec2(xpos  - noise * u_chrAb , uv.y)).b, 1.0);


    // recalculate alpha
    acc.a = max(acc.r, max(acc.g, acc.b));
    acc.a = smoothstep(0.1, 1.0, (acc.a*10.0) * (acc.a*10.0));


    gl_FragColor = acc;


}
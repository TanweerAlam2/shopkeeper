function getCart(){return Number(localStorage.getItem('shopkeeper-cart')||0)}
function setCart(v){v=Math.max(0,v);localStorage.setItem('shopkeeper-cart',v);document.querySelectorAll('#cartCount').forEach(e=>e.textContent=v)}
function toast(message){const t=document.querySelector('#toast');if(!t)return;t.textContent=message;t.classList.add('show');setTimeout(()=>t.classList.remove('show'),1800)}
document.querySelectorAll('.add').forEach(btn=>btn.addEventListener('click',()=>{setCart(getCart()+1);toast((btn.dataset.name||'Item')+' added to cart');btn.innerHTML='Added ✓';setTimeout(()=>btn.innerHTML='Add to cart <span>+</span>',1200)}))
document.querySelectorAll('#cartCount').forEach(e=>e.textContent=getCart())
const sort=document.querySelector('#sortProducts'), grid=document.querySelector('#productGrid')
if(sort&&grid)sort.addEventListener('change',()=>{const items=[...grid.children];if(sort.value==='low')items.sort((a,b)=>Number(a.dataset.price)-Number(b.dataset.price));if(sort.value==='high')items.sort((a,b)=>Number(b.dataset.price)-Number(a.dataset.price));items.forEach(i=>grid.appendChild(i))})
const menu=document.querySelector('.menu-toggle');if(menu)menu.addEventListener('click',()=>document.querySelector('.main-nav')?.classList.toggle('open'))

const loginForm=document.querySelector('#loginForm')
if(loginForm){
  const password=document.querySelector('#password'), toggle=document.querySelector('.password-toggle')
  const message=document.querySelector('#loginMessage'), nameField=document.querySelector('#fullName')
  const submit=loginForm.querySelector('.auth-submit'), hint=document.querySelector('#authHint')
  let mode='login'
  document.querySelectorAll('.mode-button').forEach(button=>button.addEventListener('click',()=>{
    mode=button.dataset.mode
    document.querySelectorAll('.mode-button').forEach(b=>b.classList.toggle('active',b===button))
    nameField.style.display=mode==='register'?'block':'none'
    nameField.required=mode==='register'
    submit.innerHTML=mode==='register'?'Create profile <span>→</span>':'Sign in <span>→</span>'
    hint.textContent=mode==='register'?'Already have an account? Choose “Sign in”.':'New to Shopkeeper? Choose “Create profile” to register.'
    message.textContent=''
  }))
  nameField.style.display='none'; nameField.required=false
  toggle?.addEventListener('click',()=>{const visible=password.type==='text';password.type=visible?'password':'text';toggle.textContent=visible?'Show':'Hide';toggle.setAttribute('aria-label',visible?'Show password':'Hide password')})
  loginForm.addEventListener('submit',async e=>{
    e.preventDefault()
    if(!loginForm.checkValidity()){message.textContent='Please complete the required fields.';message.className='form-message error';loginForm.reportValidity();return}
    const payload={email:document.querySelector('#email').value,password:password.value}
    if(mode==='register') payload.fullName=nameField.value
    try{
      const response=await fetch(mode==='register'?'/api/auth/register':'/api/auth/login',{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify(payload)})
      const data=await response.json()
      if(!response.ok) throw new Error(data.message||'Authentication failed.')
      message.textContent=data.message;message.className='form-message success'
      localStorage.setItem('shopkeeper-user',JSON.stringify(data.user))
      setTimeout(()=>window.location.href='/account',500)
    }catch(error){message.textContent=error.message;message.className='form-message error'}
  })
}
document.querySelectorAll('.social-button').forEach(button=>button.addEventListener('click',()=>{const message=document.querySelector('#loginMessage');if(message){message.textContent=button.dataset.provider+' sign-in is ready to connect.';message.className='form-message'}}))
document.querySelector('#forgotPassword')?.addEventListener('click',e=>{e.preventDefault();const message=document.querySelector('#loginMessage');if(message){message.textContent='Password reset instructions will be sent in the production authentication flow.';message.className='form-message'}})
